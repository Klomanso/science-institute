package com.example.scienceinstitute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler({ResourceNotFoundException.class})
        public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("errorMessage", e.getMessage());
                modelAndView.setViewName("exception/resourceNotFound");
                return modelAndView;
        }
}
