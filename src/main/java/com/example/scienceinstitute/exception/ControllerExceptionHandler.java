package com.example.scienceinstitute.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler({BadActionException.class})
        public ModelAndView handleResourceNotFoundException(BadActionException e) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("errorMessage", e.getMessage());
                modelAndView.setViewName("exception/resourceNotFound");
                return modelAndView;
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler({ConstraintViolationException.class})
        public ModelAndView handleResourceNotFoundException(ConstraintViolationException e) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("errorMessage", "Can't delete because it lead " +
                        "to invalid team member size");
                modelAndView.setViewName("exception/resourceNotFound");
                return modelAndView;
        }
}
