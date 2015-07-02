package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.util.FileManager;
import br.com.hackerbrasileiro.webapp.util.StreamInfo;
import br.com.hackerbrasileiro.webapp.domain.AllHackers;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/result.png")
public class DownloadHackersListController {

    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_VALUE_FORMAT = "attachment; filename=\"%s\"";
    private static final int BUFFER_SIZE = 4096;
    public static final String ALL_HACKERS_CSV = "allhackers.csv";
    private String filePathResult = System.getenv(EnvironmentVariable.FILE_PATH).concat("/").concat(ALL_HACKERS_CSV);

    private FileManager fileManager;
    private AllHackers allHackers;

    @Autowired
    public DownloadHackersListController(FileManager fileManager, AllHackers allHackers) throws IOException {
        this.fileManager = fileManager;
        this.allHackers = allHackers;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void downloadHackers(HttpServletRequest request, HttpServletResponse response) throws Exception{

        allHackers.generateCSVFile();

        StreamInfo streamInfo = fileManager.getStreamInfo(filePathResult);
        InputStream inputStream = streamInfo.getInputStream();

        String headerValue = String.format(HEADER_VALUE_FORMAT, ALL_HACKERS_CSV);
        response.setHeader(HEADER_KEY, headerValue);

        response.setContentLength((int) streamInfo.getFileSize());
        response.setContentType("text/csv");

        OutputStream output = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        output.close();
    }

}
