package davecat.controller;

import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.service.AttendanceService;
import davecat.service.CommonService;
import davecat.service.CourseService;
import davecat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Controller
public class EditController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;


    @RequestMapping("/edit")
    public String edit(Model model,
                       @RequestParam(name = "type", required = true) String type,
                       @RequestParam(name = "id", required = true) UUID id) {
        Collection<Attendance> attendances;
        Collection<Course> courses;
        Collection<User> users;
        switch (type) {
            case "course":
                attendances = attendanceService.getAttendacesForClass(id);
                users = userService.getAllUsers();
                courses = new ArrayList<>();
                model.addAttribute("title", courseService.getCourseByID(id).getTitle());
                break;
            case "user":
                attendances = attendanceService.getAttendancesForUser(id);
                courses = courseService.getAllCourses();
                System.out.println(courses);
                users = new ArrayList<>();
                model.addAttribute("title", userService.getUserByID(id).getName());
                break;
            default:
                attendances = new ArrayList<>();
                courses = new ArrayList<>();
                users = new ArrayList<>();
                break;
        }
        model.addAttribute("attendances", attendances);
        model.addAttribute("courses", courses);
        model.addAttribute("users", users);
        model.addAttribute("type", type);
        model.addAttribute("id", id);

        return "edit";
    }

    @RequestMapping("/modify")
    public String modify(Model model,
                         @RequestParam(name = "mode", required = true) String mode,
                         @RequestParam(name = "courseID", required = true) UUID courseID,
                         @RequestParam(name = "userID", required = true) UUID userID) {
        switch (mode) {
            case "add":
                commonService.addAttendance(courseID, userID);
                model.addAttribute("messageTitle", "Felhasználó hozzáadása");
                model.addAttribute("messageDescription", "");
                model.addAttribute("messageType", "success");
                model.addAttribute("messageText", "Felhasználó sikeresen hozzáadva a kurzushoz, valamint a jelenléti ív is létre lett hozva.");
                break;
            case "remove":
                commonService.removeAttendance(attendanceService.getAttendance(courseID, userID));
                model.addAttribute("messageTitle", "Felhasználó eltávolítása");
                model.addAttribute("messageDescription", "");
                model.addAttribute("messageType", "success");
                model.addAttribute("messageText", "Felhasználó sikeresen eltávolítva a kurzusból, valamint a jelenléti ív is törlésre került.");
                break;
            default:
                break;
        }

        return "message";
    }

}
