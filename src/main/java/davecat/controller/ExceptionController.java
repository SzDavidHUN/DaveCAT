package davecat.controller;

import davecat.exceptions.AttendanceAlreadyExistsException;
import davecat.exceptions.NotEmptyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

public interface ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    String EntityNotFoundExceptionHandler(Model model, HttpServletRequest req, Exception ex);

    @ExceptionHandler(NotEmptyException.class)
    String NotEmptyExceptionHandler(Model model, HttpServletRequest req, Exception ex);

    @ExceptionHandler(AttendanceAlreadyExistsException.class)
    String AttendanceAlreadyExistsExceptionHandler(Model model, HttpServletRequest req, Exception ex);
}
