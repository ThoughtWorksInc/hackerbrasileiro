package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import br.com.hackerbrasileiro.webapp.domain.PythonScript;
import br.com.hackerbrasileiro.webapp.domain.RandomHacker;
import br.com.hackerbrasileiro.webapp.controller.representation.AdminRepresentation;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@Log4j
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
        try {
            Integer numberOfPhotos = photos.countNumberOfFilesInFolder(Photos.getPhotosPath());
            return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos));
        } catch (Exception ex) {
            log.error("Admin Controller - error getting number of files in folder: ", ex);
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = "image/png")
    public @ResponseBody byte[] runScript() throws IOException, InterruptedException {
        try {
            String script = PythonScript.getScriptPath().concat("/generate.py");
            PythonScript pythonScript = new PythonScript(script, "python");
            pythonScript.execute();

            String facemorpherResult = PythonScript.getScriptPath().concat("/result.png");
            return photos.getImageAsByteArray(facemorpherResult);
        } catch (Exception ex) {
            log.error("Admin Controller - error running facemorpher: ", ex);
            return null;
        }
    }

    @RequestMapping(value = "/admin/email", method = RequestMethod.POST)
    public ModelAndView getRandomEmail() throws IOException {
        try {
            Hacker hacker = randomHacker.getRandomHacker();
            Integer numberOfPhotos = photos.countNumberOfFilesInFolder(Photos.getPhotosPath());

            return new ModelAndView("admin", "adminModel", new AdminRepresentation(numberOfPhotos, hacker.getFullName(), hacker.getEmail()));
        } catch (Exception ex) {
            log.error("Admin Controller - error getting random email: ", ex);
            return new ModelAndView("error");
        }
    }
}
