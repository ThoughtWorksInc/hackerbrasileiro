package br.com.hackerbrasileiro.webapp.domain.validator;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FileManager{

    File file;
    FileInputStream fileInputStream;

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }


    public StreamInfo getStreamInfo(String fileName) throws FileNotFoundException {
        return new StreamInfo(fileName);
    }
}
