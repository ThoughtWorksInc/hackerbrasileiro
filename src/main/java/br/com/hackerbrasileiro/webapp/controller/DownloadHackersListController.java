package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.CsvFile;
import br.com.hackerbrasileiro.webapp.domain.validator.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("downloads/hackers.csv")
public class DownloadHackersListController {

    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_VALUE_FORMAT = "attachment; filename=\"%s\"";
    private static final int BUFFER_SIZE = 4096;

    private FileManager fileManager;

    @Autowired
    public DownloadHackersListController(FileManager fileManager){
     this.fileManager = fileManager;
    }
    private String filePathResult = CsvFile.CSV_FOLDER_PATH.concat("/hackers.csv");

    public void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{

        File fileSaved = fileManager.createFile(filePathResult);

        FileInputStream inputStream = new FileInputStream(fileSaved);

        String headerValue = String.format(HEADER_VALUE_FORMAT, fileSaved.getName());

        response.setContentLength((int) fileSaved.length());
        response.setContentType("text/css");

        response.setHeader(HEADER_KEY, headerValue);
        OutputStream output = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        output.close();

        fileManager.deleteFile(filePathResult);
    }

}
