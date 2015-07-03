package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.AllHackers;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import br.com.hackerbrasileiro.webapp.util.FileHelper;
import br.com.hackerbrasileiro.webapp.util.FileManager;
import br.com.hackerbrasileiro.webapp.util.StreamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/hackerslist")
public class DownloadHackersController {

    public static final String ALL_HACKERS_CSV = "allhackers.csv";
    private String filePathResult = System.getenv(EnvironmentVariable.FILE_PATH).concat("/").concat(ALL_HACKERS_CSV);

    private FileManager fileManager;
    private AllHackers allHackers;
    private FileHelper fileHelper;

    @Autowired
    public DownloadHackersController(FileManager fileManager, AllHackers allHackers, FileHelper fileHelper) throws IOException {
        this.fileManager = fileManager;
        this.allHackers = allHackers;
        this.fileHelper = fileHelper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void downloadHackers(HttpServletResponse response) throws Exception {
        allHackers.generateCSVFile();

        StreamInfo hackersCSV = fileManager.getStreamInfo(filePathResult);

        setResponseMetadata(response, (int) hackersCSV.getFileSize());
        writeResponse(response.getOutputStream(), hackersCSV);

    }

    private void writeResponse(OutputStream output, StreamInfo streamInfo) throws IOException {
        int bufferSize = 4096;
        InputStream inputStream = streamInfo.getInputStream();

        byte[] buffer = new byte[bufferSize];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        output.close();
    }

    private void setResponseMetadata(HttpServletResponse response, int fileSize) {
        String headerValueFormat = "attachment; filename=\"%s\"";
        String headerKey = "Content-Disposition";
        String contentType = "text/csv";
        String headerValue = String.format(headerValueFormat, ALL_HACKERS_CSV);

        response.setHeader(headerKey, headerValue);
        response.setContentLength(fileSize);
        response.setContentType(contentType);
    }

}