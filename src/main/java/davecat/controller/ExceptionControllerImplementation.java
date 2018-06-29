package davecat.controller;

import davecat.exceptions.AttendanceAlreadyExistsException;
import davecat.exceptions.NotEmptyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class ExceptionControllerImplementation implements ExceptionController {

    @Override
    @ExceptionHandler(EntityNotFoundException.class)
    public String EntityNotFoundExceptionHandler(Model model, HttpServletRequest req, Exception ex) {
        return MessageController.generateMessage(model,
                "404 - Nem található",
                ex.getMessage(),
                "danger",
                "A kért objektum nem található a rendszerben!",
                false);
    }

    @Override
    @ExceptionHandler(NotEmptyException.class)
    public String NotEmptyExceptionHandler(Model model, HttpServletRequest req, Exception ex) {
        return MessageController.generateMessage(model,
                "Hiba!",
                ex.getMessage(),
                "warning",
                "A kért művelet nem hajtható végre, ugyanis jelenléti ívek tartoznak hozzá. A törléshez előbb kérlet töröld ezeket!",
                true);
    }

    @Override
    @ExceptionHandler(AttendanceAlreadyExistsException.class)
    public String AttendanceAlreadyExistsExceptionHandler(Model model, HttpServletRequest req, Exception ex) {
        return MessageController.generateMessage(model,
                "Jelenléti ív!",
                ex.getMessage(),
                "warning",
                "A jelenléti ív már létezik!",
                true);
    }


}
