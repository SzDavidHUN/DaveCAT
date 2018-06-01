package davecat.controller;

import davecat.service.CourseService;
import davecat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;

@Controller
public class MainController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("now", courseService.getCoursesAt(11, DayOfWeek.WEDNESDAY));

        return "index";
    }

    @RequestMapping("/search")
    public String searchByNeptun(Model model,
                                 @RequestParam("neptun") String neptun) {

        model.addAttribute("users", userService.getAllByNeptun(neptun));

        return "searchResults";
    }

}
