package davecat.controller;

import davecat.exceptions.AttendanceAlreadyExistsException;
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
import org.springframework.web.bind.annotation.RequestMethod;
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


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(name = "type") String type,
                       @RequestParam(name = "id") UUID id) {
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

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Model model, //TODO: RENDUNDANCI/HIBÁS CLASS
                         @RequestParam(name = "mode") String mode,
                         @RequestParam(name = "courseID") UUID courseID,
                         @RequestParam(name = "userID") UUID userID) {
        switch (mode) {
            case "add":
                model.addAttribute("messageTitle", "Jelenlét ív létrehozása");
                model.addAttribute("messageDescription", "");
                try {
                    commonService.addAttendance(courseID, userID);
                    model.addAttribute("messageType", "success");
                    model.addAttribute("messageText", "A felhasználó és kurzus sikeresen társítva lettek, a jelenléti ív létrejött.");
                } catch (AttendanceAlreadyExistsException ex) {
                    model.addAttribute("messageType", "warning");
                    model.addAttribute("messageText", "A felhasználó és kurzus társítása már korábban megtörtént, ugyanis a jelenléti ív már létezik.");
                }
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
