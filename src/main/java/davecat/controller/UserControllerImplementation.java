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
public class UserControllerImplementation implements UserController {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addGet(Model model) {
        return "addUser";
    }

    @Override
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addPost(Model model,
                          @RequestParam("name") String name,
                          @RequestParam("neptun") String neptun,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password) {
        userService.add(name, neptun, email, password);

        model.addAttribute("message", name + " nevű felhasználó sikeresen létrehozva!");

        return "addUser";
    }

    @Override
    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public String removePost(Model model,
                             @RequestParam("userID") UUID userID) {
        userService.remove(userID);

        return MessageController.generateMessage(model,
                "Felhasználó",
                "Felhasználó törlése",
                "success",
                "Felhasználó törlése sikeresen megtörtént!",
                false);
    }

    @Override
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsersGet(Model model) {
        model.addAttribute("users", userService.getAll());

        return "listUsers";
    }

    @Override
    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    public String showUser(Model model,
                           @RequestParam("userID") UUID userID) {
        model.addAttribute("user", userService.getByID(userID));

        return "showUser";
    }
}
