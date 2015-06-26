package br.com.hackerbrasileiro.webapp.domain;

import java.io.IOException;

public interface CsvFile {

    static final String FILE_NAME_OUTPUT = "src/main/resources/files/hackers_%s.cvs";
    static final String NEW_LINE = "\n";

    void save(Hacker hacker) throws IOException;
}
