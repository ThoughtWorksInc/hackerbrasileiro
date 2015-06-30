package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView createHome(Model model) {
        return new ModelAndView("home", "hacker", new Hacker());
    }

    @RequestMapping(value = "/oldhome", method = RequestMethod.GET)
    public ModelAndView createOldHome(Model model) {
        return new ModelAndView("oldhome", "hacker", new Hacker());
    }
}

