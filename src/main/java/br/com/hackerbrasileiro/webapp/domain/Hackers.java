package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.domain.validator.FileValidator;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class Hackers implements CsvFile {

    Photos photos;

    @Autowired
    public Hackers(Photos photos) {
        this.photos = photos;
    }

    public void save(Hacker hacker) throws IOException {
        String UUID = photos.save(hacker.getImageData());
        String csvLine = formatLine(hacker, UUID);
        addLine(csvLine);
    }

    @Override
    public String formatLine(Hacker hacker, String UUID) {
        return String.format("%s %s %s", hacker.toString(), UUID, NEW_LINE);
    }

    @Override
    public void addLine(String csvLine) throws IOException {
        FileValidator.createFolderIfDoesNotExistsFor(getFilePath());
        FileValidator.createFileIfDoesNotExistsFor(getName());
        FileWriter fileWriter = new FileWriter(getName(), true);
        fileWriter.write(csvLine);
        fileWriter.close();
    }

    @Override
    public String getName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        return String.format(getFilePath().concat(FILE_NAME), dateFormat.format(date));
    }

    @Override
    public List<Hacker> getHackersDaily() throws IOException {
        BufferedReader csvFile = new BufferedReader(new FileReader(getName()));
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

    @Override
    public String getFilePath() {
        return System.getenv().get(EnvironmentVariable.FILE_PATH).concat("/");
    }
}
