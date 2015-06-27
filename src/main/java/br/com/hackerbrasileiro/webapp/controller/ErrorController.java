package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception exception) {
        Errors erros = new Errors(request.getRequestURL().toString(), exception);
        return new ModelAndView("error", "error", erros);
    }
}

