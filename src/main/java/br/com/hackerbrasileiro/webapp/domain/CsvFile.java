package br.com.hackerbrasileiro.webapp.domain;

import java.io.IOException;
import java.util.List;

public interface CsvFile {

    public static final String FILE_NAME = "hackers_%s.csv";
    public static final String NEW_LINE = "\n";
    public static final String PATH = "src/main/resources/files/";

    String formatLine(Hacker hacker, String UUID);

    void addLine(String line) throws IOException;

    String getName();

    List<Hacker> getHackersDaily() throws IOException;
}
