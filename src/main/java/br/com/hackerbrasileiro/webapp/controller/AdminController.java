package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import br.com.hackerbrasileiro.webapp.domain.PythonScript;
import br.com.hackerbrasileiro.webapp.domain.RandomHacker;
import br.com.hackerbrasileiro.webapp.controller.representation.AdminRepresentation;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE)
public class AdminController {

    Photos photos;
    RandomHacker randomHacker;

    @Autowired
    public AdminController(Photos photos, RandomHacker randomHacker) {
        this.photos = photos;
        this.randomHacker = randomHacker;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView createAdminView() throws IOException {
        Integer numberOfPhotos = photos.countNumberOfFilesInFolder("photos");
        return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos));
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = "image/png")
    public @ResponseBody byte[] runScript() throws IOException, InterruptedException {
        PythonScript pythonScript = new PythonScript("facemorpher/generate.py", "python", "");
        pythonScript.execute();

        return photos.getImageAsByteArray("facemorpher/result.png");
    }

    @RequestMapping(value = "/admin/email", method = RequestMethod.POST)
    public ModelAndView getRandomEmail() throws IOException{
        Hacker hacker = randomHacker.getRandomHacker();
        Integer numberOfPhotos = photos.countNumberOfFilesInFolder("photos");

        return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos, hacker.getFullName(), hacker.getEmail()));
    }
}
