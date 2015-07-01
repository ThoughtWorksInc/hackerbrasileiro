package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Hackers;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@Controller
@FieldDefaults(level = PRIVATE)
public class HackerController {

    Hackers hackers;

    @Autowired
    public HackerController(Hackers hackers) {
        this.hackers = hackers;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Hacker hacker) throws Exception {
        try {
            hackers.save(hacker);
        } catch (Exception ex) {
            for (String envName : System.getenv().keySet()) {
                log.error(envName);
                log.error(System.getenv().get(envName));
            }
            
            log.error("exception", ex);
            // TODO: Redirect to error page
        }
        return "redirect:/";
    }
}
