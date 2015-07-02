package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;

import java.io.IOException;
import java.util.List;

public interface CsvFile {

    static final String FILE_NAME = "hackers_%s.csv";
    static final String NEW_LINE = "\n";
    static final String CSV_FOLDER_PATH = System.getenv().get(EnvironmentVariable.FILE_PATH).concat("/");

    String formatLine(Hacker hacker, String UUID);

    void addLine(String line) throws IOException;

    String getName();

    List<Hacker> getHackersDaily() throws IOException;

}
