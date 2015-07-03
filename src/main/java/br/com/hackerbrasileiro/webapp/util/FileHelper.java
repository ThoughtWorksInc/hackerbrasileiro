package br.com.hackerbrasileiro.webapp.util;


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

    public static final String ALL_HACKERS_FILE = "allhackers.csv";
    private static final String NEW_LINE = "\n";
    public static final String ALL_HACKERS_HEADER = "NOME, SOBRENOME, EMAIL, ID FOTO";


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

        if (file.exists()) {
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

    public void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public List<File> getFilesFrom(String filePath) {
        File folder = new File(filePath);

        List<File> files = new ArrayList<>();

        for (File file : folder.listFiles()) {
            if (file.isFile() && isCsv(file)) {
                files.add(file);
            }
        }

        return files;
    }

    public void merge(List<File> files) throws IOException {
        String header = ALL_HACKERS_HEADER.concat(NEW_LINE);
        addLine(header);
        for (File file : files) {
            FileInputStream stream = new FileInputStream(file.getAbsolutePath());
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while (line != null) {
                addLine(line);
                line = bufferedReader.readLine();
            }
        }
    }

    public StreamHelper getStreamFor(String fileName) throws FileNotFoundException {
        return new StreamHelper(fileName);
    }

    private String getPhotoPath() {
        return System.getenv().get(EnvironmentVariable.PHOTO_PATH).concat("/");
    }

    private boolean isCsv(File file) {
        return file.getName().endsWith(".csv");
    }

    private void addLine(String csvLine) throws IOException {
        FileWriter fileWriter = new FileWriter(getFilePath().concat(ALL_HACKERS_FILE), true);
        fileWriter.write(csvLine + NEW_LINE);
        fileWriter.close();
    }
}
