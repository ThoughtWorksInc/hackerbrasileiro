package br.com.hackerbrasileiro.webapp.util;


import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static lombok.AccessLevel.*;

@FieldDefaults(level = PRIVATE)
public class StreamHelper {
    @Getter InputStream inputStream;
    @Getter String fileName;
    @Getter long fileSize;

    public StreamHelper(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        File file = new File(fileName);
        inputStream = new FileInputStream(file);
        this.fileSize = file.length();
    }
}
