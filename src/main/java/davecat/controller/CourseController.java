package davecat.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.UUID;

public interface CourseController {
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    String addGet(Model model);

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    String addPost(Model model,
                   @RequestParam("title") String title,
                   @RequestParam("description") String description,
                   @RequestParam("location") String location,
                   @RequestParam("length") Integer length,
                   @RequestParam("day") DayOfWeek day,
                   @RequestParam("begin") Integer begin,
                   @RequestParam("end") Integer end);

    @RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
    String removePost(Model model,
                      @RequestParam("courseID") UUID courseID);

    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    String editGet(Model model,
                   @RequestParam("courseID") UUID courseID);

    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    String editPost(Model model,
                    @RequestParam("courseID") UUID courseID,
                    @RequestParam(value = "title", required = false) String title,
                    @RequestParam(value = "description", required = false) String description,
                    @RequestParam(value = "location", required = false) String location,
                    @RequestParam(value = "length", required = false) Integer length,
                    @RequestParam(value = "day", required = false) DayOfWeek day,
                    @RequestParam(value = "begin", required = false) Integer begin,
                    @RequestParam(value = "end", required = false) Integer end);

    @RequestMapping(value = "/listCourses", method = RequestMethod.GET)
    String listCourses(Model model);

    @RequestMapping(value = "/showCourse", method = RequestMethod.GET)
    String showCourse(Model model,
                      @RequestParam("courseID") UUID courseID);
}
