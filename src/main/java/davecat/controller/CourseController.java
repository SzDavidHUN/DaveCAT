package davecat.controller;

import davecat.modell.Course;
import davecat.repository.CourseRepository;
import davecat.service.AttendanceService;
import davecat.service.CommonService;
import davecat.service.CourseService;
import davecat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.UUID;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET) //TODO: MINDENHOL REQUESTMETHOD
    public String getCourse(@RequestParam(name = "id", required = true) String id, Model model) {
        //Course course = courseService.getUserByID(UUID.fromString(id));
        Course course = courseService.getCourseByID(UUID.fromString(id));
        model.addAttribute("courseTitle", course.getTitle());
        model.addAttribute("courseTime", course.getTime());
        model.addAttribute("coursePlace", course.getLocation());
        model.addAttribute("courseDescription", course.getDescription());
        model.addAttribute("courseID", course.getId());
        model.addAttribute("attendances", attendanceService.getAttendacesForClass(UUID.fromString(id)));
        model.addAttribute("courseDay", course.getDayString());
        model.addAttribute("courseBegin", course.getBegin());
        model.addAttribute("courseEnd", course.getEnd());
        return "course";
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCourse(Model model) {
        model.addAttribute("messageBox", false);
        model.addAttribute("message", "");
        System.out.println("GET");
        return "addCourse";
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourseForReal(
            Model model,
            @RequestParam(name = "courseTitle", required = true) String courseTitle,
            @RequestParam(name = "courseDescription", required = true) String courseDescription,
            @RequestParam(name = "courseLocation", required = true) String courseLocation,
            @RequestParam(name = "courseTime", required = true) String courseTime,
            @RequestParam(name = "courseLength", required = true) String courseLength,
            @RequestParam(name = "courseDay", required = true) DayOfWeek courseDay,
            @RequestParam(name = "courseBegin", required = true) Integer courseBegin,
            @RequestParam(name = "courseEnd", required = true) Integer courseEnd
    ) {
        courseService.saveCourse(
                new Course(
                        courseTitle,
                        courseDescription,
                        courseLocation,
                        courseTime,
                        Integer.parseInt(courseLength),
                        courseDay,
                        courseBegin,
                        courseEnd
                )
        );

        model.addAttribute("messageBox", true);
        model.addAttribute("message", "Kurzus sikeresen hozzáadva!");
        System.out.println("POST");

        return "addCourse";
    }

    @RequestMapping("/editParticipants")
    public String editParticipants(
            Model model,
            @RequestParam(value = "id", required = true) UUID id
    ) {
        model.addAttribute("courseID", id);
        model.addAttribute("currentUsers", courseService.getStudentsForCourse(id));
        model.addAttribute("allUsers", userService.getAllUsers());

        return "editParticipants";
    }

    @RequestMapping("/editCourse")
    public String editCourse(
            Model model,
            @RequestParam(value = "mode", required = true) String mode,
            @RequestParam(value = "courseID", required = true) UUID courseID,
            @RequestParam(value = "userID", required = true) UUID userID
    ) {

        switch (mode) {
            case "addUser":
                commonService.addUserToCourse(userID, courseID);
                commonService.addAttendance(courseID, userID);
                model.addAttribute("messageTitle", "Kurzus módosítása");
                model.addAttribute("messageDescription", "Felhasználó hozzárendelés a kurzushoz");
                model.addAttribute("messageType", "success");
                model.addAttribute("messageText", "Felhasználó hozzáadása a kurzushoz iskeresen megtörtént!");
                break;
        }

        return "message";
    }
}
