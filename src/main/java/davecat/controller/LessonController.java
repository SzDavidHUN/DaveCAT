package davecat.controller;

import davecat.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/addLesson", method = RequestMethod.GET)
    public String addGet(Model model) {

        return "addLesson";
    }

    @RequestMapping(value = "/addLesson", method = RequestMethod.POST)
    public String addPost(Model model,
                          @RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("render") String render,
                          @RequestParam(value = "present", required = false, defaultValue = "false") Boolean present,
                          @RequestParam(value = "away", required = false, defaultValue = "false") Boolean away) {

        lessonService.add(name, description, render, present, away);

        model.addAttribute("message", "Jelenlét létrehozása sikeresen megtörtént!");

        return "addLesson";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removePost(Model model,
                             @RequestParam("lessonID") UUID lessonID) {

        lessonService.remove(lessonID);

        return MessageController.generateMessage(model,
                "Jelenlét",
                "Jelenlét törlése",
                "success",
                "Jelenlét törlése sikeresen megtörtént!");
    }

    @RequestMapping(value = "/listLessons", method = RequestMethod.GET)
    public String listLessons(Model model) {
        model.addAttribute("lessons", lessonService.getAll());

        return "listLessons";
    }

    @RequestMapping(value = "/showLesson", method = RequestMethod.GET)
    public String showLesson(Model model,
                             @RequestParam("lessonID") UUID lessonID) {
        model.addAttribute("lesson", lessonService.getByID(lessonID));

        return "showLesson";
    }

}
