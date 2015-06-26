package br.com.hackerbrasileiro.webapp.domain;

import java.io.IOException;

public interface CsvFile {

    void save(Hacker hacker) throws IOException;
}
