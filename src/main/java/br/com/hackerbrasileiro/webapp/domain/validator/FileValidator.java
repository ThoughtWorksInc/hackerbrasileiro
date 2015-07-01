package br.com.hackerbrasileiro.webapp.domain.validator;


import java.io.File;
import java.io.IOException;

public class FileValidator {

    public static void createFolderIfDoesNotExistsFor(String folderName) {
        File folderFile = new File(folderName);
        if (!folderFile.exists()) {
            folderFile.mkdir();
        }
    }

    public static void createFileIfDoesNotExistsFor(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

}
