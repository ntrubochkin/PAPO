package com.noname.books_exchange.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            Integer code = Integer.valueOf(status.toString());
            if(code == HttpStatus.NOT_FOUND.value()) {
                return "error-404"; //TODO
            }
            if(code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"; //TODO
            }
        }
        return "error"; //TODO
    }
}
