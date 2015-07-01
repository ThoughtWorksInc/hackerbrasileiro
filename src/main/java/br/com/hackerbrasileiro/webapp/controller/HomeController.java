package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView createHome(Model model) {
        try {
            return new ModelAndView("home", "hacker", new Hacker());
        } catch (Exception ex) {
            log.error("Home Controller - error accessing home page: ", ex);
            return new ModelAndView("error");
        }
    }
}
