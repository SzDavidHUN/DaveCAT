package davecat.controller;

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
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(
            Model model,
            @RequestParam(name = "id") UUID id
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
            @RequestParam(name = "userName") String userName,
            @RequestParam(name = "userNeptun") String userNeptun,
            @RequestParam(name = "userEmail") String userEmail,
            @RequestParam(name = "userPassword") String userPassword
    ) {
        model.addAttribute("messageTitle", "Regisztráció");
        model.addAttribute("messageDescription", "Új felhasználó regisztrálása");
        if (userName.isEmpty() || userNeptun.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {

            model.addAttribute("messageType", "danger");
            model.addAttribute("messageText", "Felhasználó felhasználó regisztrálása sikertelen: Egy vagy több mező üres!");
            return "message";
        }
        userService.add(userName, userNeptun, userEmail, userPassword);
        model.addAttribute("messageType", "success");
        model.addAttribute("messageText", "Felhasználó felhasználó regisztrálása sikeresen megtörtént!");

        return "message";
    }
}
