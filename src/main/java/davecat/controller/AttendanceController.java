package davecat.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface AttendanceController {
    @RequestMapping(value = "/addAttendance", method = RequestMethod.POST)
    String addPost(
            Model model,
            @RequestParam("courseID") UUID courseID,
            @RequestParam("userID") UUID userID
    );

    @RequestMapping(value = "/addAttendance", method = RequestMethod.GET)
    String addGet(
            Model model,
            @RequestParam(value = "courseID", required = false) UUID courseID,
            @RequestParam(value = "userID", required = false) UUID userID
    );

    @RequestMapping(value = "/removeAttendance", method = RequestMethod.POST)
    String remove(Model model,
                  @RequestParam("attendanceID") UUID attendanceID
    );

    @RequestMapping(value = "/setAttendance", method = RequestMethod.GET)
    String set(Model model,
               @RequestParam("attendanceID") UUID attendanceID);

    @RequestMapping(value = "/setAttendance", method = RequestMethod.POST)
    String set(Model model,
               @RequestParam("attendanceID") UUID attendanceID,
               @RequestParam("occasion") Integer occasion,
               @RequestParam("lessonID") UUID lessonID);

    @RequestMapping(value = "/showAttendance", method = RequestMethod.GET, params = "attendanceID")
    String show(Model model,
                @RequestParam("attendanceID") UUID attendanceID);

    @RequestMapping(value = "/showAttendance", method = RequestMethod.GET, params = {"courseID", "userID"})
    String show2(Model model,
                 @RequestParam("courseID") UUID courseID,
                 @RequestParam("userID") UUID userID);
    @RequestMapping(value = "/batchAttendance", method = RequestMethod.GET)
    public String batchAttendanceGet(Model model,
                                     @RequestParam("courseID") UUID courseID);

}
