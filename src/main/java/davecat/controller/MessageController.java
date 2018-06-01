package davecat.controller;

import org.springframework.ui.Model;

public class MessageController {

    public static String generateMessage(Model model, String title, String description, String alertType, String alertText) {

        model.addAttribute("messageTitle", title);
        model.addAttribute("messageDescription", description);
        model.addAttribute("messageType", alertType);
        model.addAttribute("messageText", alertText);

        return "message";
    }
}
