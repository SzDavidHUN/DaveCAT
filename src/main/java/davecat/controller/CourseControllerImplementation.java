package davecat.controller;

import davecat.modell.Course;
import davecat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.UUID;

@Controller
public class CourseControllerImplementation implements CourseController {

    @Autowired
    private CourseService courseService;

    @Override
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addGet(Model model) {
        return "addCourse";
    }

    @Override
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addPost(Model model,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("location") String location,
                          @RequestParam("length") Integer length,
                          @RequestParam("day") DayOfWeek day,
                          @RequestParam("begin") Integer begin,
                          @RequestParam("end") Integer end) {
        courseService.add(title, description, location, length, day, begin, end);

        model.addAttribute("message", "A(z) " + title + " nevű kurzus létrehozása sikeresen megtörtént!");

        return "addCourse";
    }

    @Override
    @RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
    public String removePost(Model model,
                             @RequestParam("courseID") UUID courseID) {
        courseService.remove(courseID);
        return MessageController.generateMessage(model,
                "Kurzus",
                "Kurzus törlése",
                "success",
                "Kurzus törlése sikeresen megtörtént!",
                false);
    }

    @Override
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public String editGet(Model model,
                          @RequestParam("courseID") UUID courseID) {
        return "editCourse";
    }

    @Override
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public String editPost(Model model,
                           @RequestParam("courseID") UUID courseID,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "location", required = false) String location,
                           @RequestParam(value = "length", required = false) Integer length,
                           @RequestParam(value = "day", required = false) DayOfWeek day,
                           @RequestParam(value = "begin", required = false) Integer begin,
                           @RequestParam(value = "end", required = false) Integer end) {

        Course course = courseService.getByID(courseID);

        if (!title.isEmpty())
            course.setTitle(title);
        if (!description.isEmpty())
            course.setDescription(description);
        if (!location.isEmpty())
            course.setLocation(location);


        return MessageController.generateMessage(model,
                "Kurzus",
                "Kurzus módosítása",
                "warning",
                "Kurzus módosítása sikeresen megtörtént!",
                false);
    }

    @Override
    @RequestMapping(value = "/listCourses", method = RequestMethod.GET)
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }

    @Override
    @RequestMapping(value = "/showCourse", method = RequestMethod.GET)
    public String showCourse(Model model,
                             @RequestParam("courseID") UUID courseID) {

        model.addAttribute("course", courseService.getByID(courseID));
        return "showCourse";
    }


}
