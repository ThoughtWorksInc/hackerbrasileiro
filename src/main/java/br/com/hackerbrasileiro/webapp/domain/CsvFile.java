package br.com.hackerbrasileiro.webapp.domain;

import java.io.IOException;
import java.util.List;

public interface CsvFile {

    static final String FILE_NAME = "hackers_%s.csv";
    static final String NEW_LINE = "\n";

    String formatLine(Hacker hacker, String UUID);

    void addLine(String line) throws IOException;

    String getName();

    List<Hacker> getHackersDaily() throws IOException;

    String getFilePath();
}
