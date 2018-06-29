package davecat.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface UserController {
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    String addGet(Model model);

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    String addPost(Model model,
                   @RequestParam("name") String name,
                   @RequestParam("neptun") String neptun,
                   @RequestParam("email") String email,
                   @RequestParam("password") String password);

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    String removePost(Model model,
                      @RequestParam("userID") UUID userID);

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    String listUsersGet(Model model);

    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    String showUser(Model model,
                    @RequestParam("userID") UUID userID);
}
