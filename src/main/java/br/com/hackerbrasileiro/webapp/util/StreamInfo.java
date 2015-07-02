package br.com.hackerbrasileiro.webapp.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StreamInfo {
    InputStream inputStream;
    String fileName;
    long fileSize;

    public StreamInfo(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        File file = new File(fileName);
        inputStream = new FileInputStream(file);
        this.fileSize = file.length();
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

}
