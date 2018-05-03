package davecat.controller;

import davecat.modell.User;
import davecat.repository.UserRepository;
import davecat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(
            Model model,
            @RequestParam(name = "id", required = true) UUID id
    ) {
        model.addAttribute("user", userService.getUserByID(id));
        model.addAttribute("userID", id);

        return "user";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String registerUser(
            Model model
    ) {

        return "reg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registerUserForReal(
            Model model,
            @RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "userNeptun", required = true) String userNeptun,
            @RequestParam(name = "userEmail", required = true) String userEmail,
            @RequestParam(name = "userPassword", required = true) String userPassword
    ) {
        userRepository.save(
                new User(
                        userName,
                        userNeptun,
                        userEmail,
                        userPassword,
                        User.Role.STUDENT
                )
        );

        return "reg";
    }
}
