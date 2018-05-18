package davecat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(java.lang.NumberFormatException.class)
    public String NumberFormatExceptionHandler() {
        return "";
    }

    /*@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Anyád pitsája!")*/
    @ExceptionHandler
    public String Exception(Model model, HttpServletRequest req, Exception ex) {
        System.out.println("Yolo");

        model.addAttribute("messageTitle", "Hiba!");
        model.addAttribute("messageDescription", "Hiba történt a kérés feldolgázsa során!");
        model.addAttribute("messageType", "danger");
        model.addAttribute("messageText", ex.getMessage());

        return "message";
    }

}
