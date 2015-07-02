package br.com.hackerbrasileiro.webapp.util;


import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHelper {

    private static final String FILE_NAME = "hackers_%s.csv";
    private static final String FILE_NAME_EXTENSION = ".jpg";

    public void createFolderIfDoesNotExistsFor(String folderName) {
        File folderFile = new File(folderName);
        if (!folderFile.exists()) {
            folderFile.mkdir();
        }
    }

    public void createFileIfDoesNotExistsFor(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void createFor(byte[] image, String fileName) throws IOException {
        File file = new File(formatPath(fileName, FILE_NAME_EXTENSION));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(image);
        fileOutputStream.close();
    }

    public void getFileWriter(String line) throws IOException {
        FileWriter fileWriter = new FileWriter(getName(), true);
        fileWriter.write(line);
        fileWriter.close();
    }

    public List<String> readFile() throws IOException {
        File file = new File(getName());
        List<String> lines = new ArrayList<>();

        if(file.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(getName()));


            try {
                String line = fileReader.readLine();

                while (line != null) {
                    lines.add(line);
                    line = fileReader.readLine();
                }
            } finally {
                fileReader.close();
            }
        }

        return lines;
    }

    public static String getName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        return String.format(getFilePath().concat(FILE_NAME), dateFormat.format(date));
    }

    public static String getFilePath() {
        return System.getenv().get(EnvironmentVariable.FILE_PATH).concat("/");
    }

    public String formatPath(String fileName, String extension) {
        createFolderIfDoesNotExistsFor(getPhotoPath());
        return String.format("%s%s%s", getPhotoPath(), fileName, extension);
    }

    private String getPhotoPath() {
        return System.getenv().get(EnvironmentVariable.PHOTO_PATH).concat("/");
    }
}
