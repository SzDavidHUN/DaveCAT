package davecat.controller;

import org.springframework.ui.Model;

public interface MessageController {
    static String generateMessage(Model model, String title, String description, String alertType, String alertText, boolean backMode) {

        model.addAttribute("messageTitle", title);
        model.addAttribute("messageDescription", description);
        model.addAttribute("messageType", alertType);
        model.addAttribute("messageText", alertText);
        model.addAttribute("backMode", backMode);

        return "message";
    }
}
