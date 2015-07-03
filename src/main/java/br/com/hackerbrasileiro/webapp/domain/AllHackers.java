package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import br.com.hackerbrasileiro.webapp.util.FileManager;
import org.springframework.stereotype.Component;    

import java.io.*;
import java.util.List;


@Component
public class AllHackers {

    public static final String ALL_HACKERS_FILE = "allhackers.csv";
    public static final String HACKERS_LIST_FOLDER = System.getenv(EnvironmentVariable.FILE_PATH).concat("/");
    private static final String NEW_LINE = "\n";
    public static final String ALL_HACKERS_HEADER = "NOME, SOBRENOME, EMAIL, ID FOTO";

    private FileManager fileManager = new FileManager();

    public File generateCSVFile() throws IOException {
        // I want to add them here!
        //String desiredFolder = System.getenv(EnvironmentVariable.FILE_PATH);
        //fileHelper.createFolderIfDoesNotExistsFor(desiredFolder);

        fileManager.deleteFile(HACKERS_LIST_FOLDER.concat(ALL_HACKERS_FILE));

        List<File> hackersInfoPerDay =  fileManager.getAllFilesFromFolder(HACKERS_LIST_FOLDER);

        fileManager.createFile(HACKERS_LIST_FOLDER.concat(ALL_HACKERS_FILE));

        mergeAllCSVFiles(hackersInfoPerDay);
        return new File(HACKERS_LIST_FOLDER.concat(ALL_HACKERS_FILE));

    }

    private void mergeAllCSVFiles(List<File> files) throws IOException {
        String csvHeader = ALL_HACKERS_HEADER.concat(NEW_LINE);
        addLine(csvHeader);
        for (File file : files) {
            FileInputStream stream = new FileInputStream(file.getAbsolutePath());
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linha = bufferedReader.readLine();

            while(linha != null) {
                addLine(linha);
                linha = bufferedReader.readLine();
            }
        }

    }

    private void addLine(String csvLine) throws IOException {
        FileWriter fileWriter = new FileWriter(HACKERS_LIST_FOLDER + ALL_HACKERS_FILE, true);
        fileWriter.write(csvLine + NEW_LINE);
        fileWriter.close();
    }
}
