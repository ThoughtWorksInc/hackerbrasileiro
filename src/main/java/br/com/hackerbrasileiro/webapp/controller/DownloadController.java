package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hackers;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import br.com.hackerbrasileiro.webapp.domain.PythonScript;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import br.com.hackerbrasileiro.webapp.util.FileHelper;
import br.com.hackerbrasileiro.webapp.util.StreamHelper;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@FieldDefaults(level = PRIVATE)
@Controller
public class DownloadController {

    static final String ALL_HACKERS_CSV = "allhackers.csv";
    static final String PHOTO_NAME = "hackerbrasileiro.png";

    Hackers hackers;
    FileHelper fileHelper;
    Photos photos;

    @Autowired
    public DownloadController(Hackers hackers, FileHelper fileHelper, Photos photos) throws IOException {
        this.hackers = hackers;
        this.fileHelper = fileHelper;
        this.photos = photos;
    }

    @RequestMapping(value = "/downloadCsv", method = RequestMethod.GET)
    public void downloadCsv(HttpServletResponse response) throws Exception {
        try {
            hackers.generateCsvFile();

            String filePath = System.getenv(EnvironmentVariable.FILE_PATH).concat("/").concat(ALL_HACKERS_CSV);
            StreamHelper hackersCsv = getStreamFor(filePath);

            String contentType = "text/csv";
            setResponseMetadata(response, contentType, (int) hackersCsv.getFileSize(), ALL_HACKERS_CSV);
            writeResponse(hackersCsv.getInputStream(), response.getOutputStream());
        } catch (Exception ex) {
            log.error("Download Controller - error generating csv file.");
        }
    }

    @RequestMapping(value = "/downloadPhoto", method = RequestMethod.GET)
    public void downloadPhoto(HttpServletResponse response) throws IOException, InterruptedException {
        try {
            String script = PythonScript.getScriptPath().concat("/generate.py");
            PythonScript pythonScript = new PythonScript(script, "python");
            pythonScript.execute();

            String photoUrl = PythonScript.getScriptPath().concat("/result.png");
            byte[] imageAsByteArray = photos.getImageAsByteArray(photoUrl);

            String contentType = "image/png";
            setResponseMetadata(response, contentType, imageAsByteArray.length, PHOTO_NAME);
            FileInputStream fileInputStream = new FileInputStream(photoUrl);
            writeResponse(fileInputStream, response.getOutputStream());
        } catch (Exception ex) {
            log.error("Download Controller - error running facemorpher: ", ex);
        }
    }

    private void writeResponse(InputStream input, OutputStream output) throws IOException {
        int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;

        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        input.close();
        output.close();
    }

    private void setResponseMetadata(HttpServletResponse response, String contentType, int contentLength, String fileName) {
        String headerValueFormat = "attachment; filename=\"%s\"";
        String headerKey = "Content-Disposition";
        String headerValue = String.format(headerValueFormat, fileName);

        response.setHeader(headerKey, headerValue);
        response.setContentLength(contentLength);
        response.setContentType(contentType);
    }

    private StreamHelper getStreamFor(String path) throws FileNotFoundException {
        return fileHelper.getStreamFor(path);
    }
}
