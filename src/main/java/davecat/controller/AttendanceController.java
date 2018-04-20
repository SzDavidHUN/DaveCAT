package davecat.controller;

import davecat.modell.Attendance;
import davecat.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @RequestMapping("/editAttendance")
    public String editAttendance(
            Model model,
            @RequestParam(value = "id") UUID id
    ) {
        Attendance attendance = attendanceService.getAttendance(id);
        if (attendance == null)
            return "editAttendance";

        int away = 0;
        int present = 0;

        for (davecat.modell.Attendance.Status status : attendance.getLessons()) {
            switch (status) {
                case AWAY:
                    away++;
                    break;
                case PRESENT:
                    present++;
                    break;
                default:
                    break;
            }

        }

        model.addAttribute("away", away);
        model.addAttribute("present", present);
        model.addAttribute("attendance", attendance);

        return "editAttendance";
    }

    @RequestMapping("/setAttendance")
    public String setAttendance(
            Model model,
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "lesson") int lesson,
            @RequestParam(name = "status") Attendance.Status status
    ) {
        attendanceService.setLesson(id, lesson, status);

        model.addAttribute("messageTitle", "Jelenlét módosítása");
        model.addAttribute("messageDescription", "Jelenlét sikeresen módosítva");
        model.addAttribute("messageType", "success");
        model.addAttribute("messageText", "A kért jelenlét módosítása sikeresen megtörtént.!");

        switch (status) {
            case AWAY:
                break;
            case PRESENT:
                break;
            case EMPTY:
                break;
            default:
                System.out.println("Cucc: " + status);
                break;
        }

        return "message";
    }

}
