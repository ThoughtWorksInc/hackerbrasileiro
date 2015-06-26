package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Hackers;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE)
public class HomeController {
    Hackers hackers;

    @Autowired
    public HomeController(Hackers hackers) {
        this.hackers = hackers;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView createHome(Model model) {
        return new ModelAndView("home", "hacker", new Hacker());
    }

    @RequestMapping(value = "/newhome", method = RequestMethod.GET)
    public String createNewHome(Model model) {
        return "newhome";
    }


    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public ModelAndView greetingSubmit(@ModelAttribute Hacker hacker, Model model) throws IOException {
        hackers.save(hacker);
        return new ModelAndView("thankyou", "hacker", hacker);
    }
}

