package davecat.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model);

    @RequestMapping("/search")
    String searchByNeptun(Model model,
                          @RequestParam("neptun") String neptun);
}
