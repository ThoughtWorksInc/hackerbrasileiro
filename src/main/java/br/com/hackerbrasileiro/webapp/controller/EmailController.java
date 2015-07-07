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

import br.com.hackerbrasileiro.webapp.controller.representation.AdminRepresentation;
import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import br.com.hackerbrasileiro.webapp.domain.RandomHacker;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@Controller
@FieldDefaults(level = PRIVATE)
public class EmailController {

    Photos photos;
    RandomHacker randomHacker;

    @Autowired
    public EmailController(Photos photos, RandomHacker randomHacker) {
        this.photos = photos;
        this.randomHacker = randomHacker;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView createAdminView() throws IOException {
        try {
            Integer numberOfPhotos = photos.countNumberOfFilesInFolder(Photos.getPhotosPath());
            return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos));
        } catch (Exception ex) {
            log.error("Admin Controller - error getting number of files in folder: ", ex);
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/admin/email", method = RequestMethod.GET)
    public ModelAndView getRandomEmail(Model model) throws IOException {
        try {
            Hacker hacker = randomHacker.getRandomHacker();
            Integer numberOfPhotos = photos.countNumberOfFilesInFolder(Photos.getPhotosPath());

            if(hacker.getEmail() == null || hacker.getEmail().isEmpty()) {
                model.addAttribute("messageNoHackers", "Desculpe, não há hackers para sortear no momento.");
            }

            return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos, hacker.getFullName(), hacker.getEmail()));
        } catch (Exception ex) {
            log.error("Admin Controller - error getting random email: ", ex);
            return new ModelAndView("error");
        }
    }
}
