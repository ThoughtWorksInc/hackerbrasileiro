package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.domain.validator.FileValidator;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class Hackers implements CsvFile {

    public static final String PATH = "src/main/resources/files/";
    public static final String FILE_NAME = "hackers_%s.csv";
    public static final String NEW_LINE = "\n";

    Photos photos;

    @Autowired
    public Hackers(Photos photos) {
        this.photos = photos;
    }

    @Override
    public void save(Hacker hacker) throws IOException {
        String UUID = photos.save(hacker.getImageData());
        String csvLine = formatLine(hacker, UUID);
        addLine(csvLine);
    }

    public List<Hacker> getDailyList() throws IOException {
        BufferedReader csvFile = new BufferedReader(new FileReader(getFileName()));
        List<Hacker> hackers = new ArrayList<>();

        try {
            String line = csvFile.readLine();

            while (line != null) {
                Hacker hacker = new Hacker();
                String[] info = line.split(",");

                hacker.setFirstName(info[0].trim());
                hacker.setLastName(info[1].trim());
                hacker.setEmail(info[2].trim());

                hackers.add(hacker);

                line = csvFile.readLine();
            }
        } finally {
            csvFile.close();
        }

        return hackers;
    }

    private String getFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        return String.format(PATH.concat(FILE_NAME), dateFormat.format(date));
    }

    private String formatLine(Hacker hacker, String UUID) {
        return String.format("%s %s %s", hacker.toString(), UUID, NEW_LINE);
    }

    private void addLine(String csvLine) throws IOException {
        FileValidator.createFolderIfDoesNotExistsFor(PATH);
        FileValidator.createFileIfDoesNotExistsFor(getFileName());
        FileWriter fileWriter = new FileWriter(getFileName(), true);
        fileWriter.write(csvLine);
        fileWriter.close();
    }
}
