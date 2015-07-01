package br.com.hackerbrasileiro.webapp.domain.validator;

import java.io.File;
import java.io.IOException;

public class FileManager {


    private int fileLength;

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

}
