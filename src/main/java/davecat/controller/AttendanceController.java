package davecat.controller;

import davecat.modell.Attendance;
import davecat.service.AttendanceService;
import davecat.service.CourseService;
import davecat.service.LessonService;
import davecat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/addAttendance", method = RequestMethod.POST)
    public String addPost(
            Model model,
            @RequestParam("courseID") UUID courseID,
            @RequestParam("userID") UUID userID
    ) {
        attendanceService.add(courseID, userID);

        return MessageController.generateMessage(model,
                "Jelenléti ív",
                "Jelenléti ív létrehozás",
                "success",
                "Jelenléti ív létrehozása sikeresen megtörtént!");
    }

    @RequestMapping(value = "/addAttendance", method = RequestMethod.GET)
    public String addGet(
            Model model,
            @RequestParam(value = "courseID", required = false) UUID courseID,
            @RequestParam(value = "userID", required = false) UUID userID
    ) {
        if (userID != null && courseID == null) {
            model.addAttribute("courses", courseService.getAll());
            model.addAttribute("user", userService.getByID(userID));
            return "addAttendanceToUser";
        } else if (userID == null && courseID != null) {
            model.addAttribute("users", userService.getAll());
            model.addAttribute("course", courseService.getByID(courseID));
            return "addAttendanceToCourse";
        } else {
            return MessageController.generateMessage(model,
                    "Hiba",
                    "2 paraméter csak POST esetén támogatott",
                    "warning",
                    "Hibás lekérés!");
        }
    }

    @RequestMapping(value = "/removeAttendance", method = RequestMethod.POST)
    public String remove(Model model,
                         @RequestParam("attendanceID") UUID attendanceID
    ) {

        attendanceService.remove(attendanceID);

        return MessageController.generateMessage(model,
                "Jelenléti ív",
                "Jelenléti ív törlése",
                "success",
                "Jelenléti ív törlése sikeresen megtörtént!");
    }

    @RequestMapping(value = "/setAttendance", method = RequestMethod.GET)
    public String set(Model model,
                      @RequestParam("attendanceID") UUID attendanceID) {

        model.addAttribute("attendance", attendanceService.getByID(attendanceID));
        model.addAttribute("lessons", lessonService.getAll());

        return "setAttendance";
    }

    @RequestMapping(value = "/setAttendance", method = RequestMethod.POST)
    public String set(Model model,
                      @RequestParam("attendanceID") UUID attendanceID,
                      @RequestParam("occasion") Integer occasion,
                      @RequestParam("lessonID") UUID lessonID) {

        attendanceService.setLesson(attendanceID, occasion, lessonID);

        return MessageController.generateMessage(model,
                "Jelenléti ív",
                "Jelenlét beállítása",
                "warning",
                "Jelenlét státuszának beállítása sikeresen megtörtént volna ha implementálva lenne!");
    }

    @RequestMapping(value = "/showAttendance", method = RequestMethod.GET, params = "attendanceID")
    public String show(Model model,
                       @RequestParam("attendanceID") UUID attendanceID) {
        Attendance attendance = attendanceService.getByID(attendanceID);

        model.addAttribute("attendance", attendance);
        model.addAttribute("present", attendanceService.getPresent(attendance));
        model.addAttribute("away", attendanceService.getAway(attendance));


        return "showAttendance";
    }

    @RequestMapping(value = "/showAttendance", method = RequestMethod.GET, params = {"courseID", "userID"})
    public String show2(Model model,
                        @RequestParam("courseID") UUID courseID,
                        @RequestParam("userID") UUID userID) {
        return show(model, attendanceService.getByID(courseID, userID).getId());
    }
}
