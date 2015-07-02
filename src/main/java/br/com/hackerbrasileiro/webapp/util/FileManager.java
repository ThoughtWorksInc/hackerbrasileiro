package br.com.hackerbrasileiro.webapp.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileManager{

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }


    public StreamInfo getStreamInfo(String fileName) throws FileNotFoundException {
        return new StreamInfo(fileName);
    }

    public List<File> getAllFilesFromFolder(String folderPath) {
        File folder = new File(folderPath);

        List<File> listOfFiles = new ArrayList<>();

        for (File file : folder.listFiles()){
            if (file.isFile() && isCSV(file))
            {
                listOfFiles.add(file);
            }
        }

        return listOfFiles;
    }

    private boolean isCSV(File file){
        return file.getName().endsWith(".csv");
    }
}
