/*
Copyright (C) 2015  ThoughtWorks, Inc.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
            return "redirect:/";
        } catch (Exception ex) {
            log.error("Hacker Controller - error saving the hacker: ", ex);
            return  "redirect:error";
        }
    }
}
