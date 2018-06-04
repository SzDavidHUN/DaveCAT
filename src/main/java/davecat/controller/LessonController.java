package davecat.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface LessonController {
    @RequestMapping(value = "/addLesson", method = RequestMethod.GET)
    String addGet(Model model);

    @RequestMapping(value = "/addLesson", method = RequestMethod.POST)
    String addPost(Model model,
                   @RequestParam("name") String name,
                   @RequestParam("description") String description,
                   @RequestParam("render") String render,
                   @RequestParam(value = "present", required = false, defaultValue = "false") Boolean present,
                   @RequestParam(value = "away", required = false, defaultValue = "false") Boolean away);

    @RequestMapping(value = "/removeLesson", method = RequestMethod.POST)
    String removePost(Model model,
                      @RequestParam("lessonID") UUID lessonID);

    @RequestMapping(value = "/listLessons", method = RequestMethod.GET)
    String listLessons(Model model);

    @RequestMapping(value = "/showLesson", method = RequestMethod.GET)
    String showLesson(Model model,
                      @RequestParam("lessonID") UUID lessonID);
}
