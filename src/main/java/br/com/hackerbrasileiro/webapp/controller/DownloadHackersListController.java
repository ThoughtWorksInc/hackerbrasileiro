package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.validator.FileManager;
import br.com.hackerbrasileiro.webapp.domain.validator.StreamInfo;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller
@RequestMapping("/result.png")
public class DownloadHackersListController {

    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_VALUE_FORMAT = "attachment; filename=\"%s\"";
    private static final int BUFFER_SIZE = 4096;
    private String filePathResult = System.getenv(EnvironmentVariable.FILE_PATH).concat("/hackers_20150702.csv");

    private FileManager fileManager;
    @Autowired
    public DownloadHackersListController(FileManager fileManager){
     this.fileManager = fileManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void downloadHackers(HttpServletRequest request, HttpServletResponse response) throws Exception{

        StreamInfo streamInfo = fileManager.getStreamInfo(filePathResult);

        String headerValue = String.format(HEADER_VALUE_FORMAT, streamInfo.getFileName());
        response.setHeader(HEADER_KEY, headerValue);

        response.setContentLength((int) streamInfo.getFileSize());
        response.setContentType("text/csv");

        OutputStream output = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = streamInfo.getInputStream().read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        streamInfo.getInputStream().close();
        output.close();
    }

}
