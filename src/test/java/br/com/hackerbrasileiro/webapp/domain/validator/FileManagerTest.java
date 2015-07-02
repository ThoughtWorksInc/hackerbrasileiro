package br.com.hackerbrasileiro.webapp.domain.validator;

import br.com.hackerbrasileiro.webapp.util.FileManager;
import br.com.hackerbrasileiro.webapp.util.StreamInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {

    @Mock
    StreamInfo streamTest;

    FileManager fileManager;

    @Before
    public void setUp() throws Exception {
        fileManager = new FileManager();
    }

    @Test
    public void shouldCreateAndDeleteFile() throws Exception {
        fileManager.createFile("teste.csv");
        assertTrue(new File("teste.csv").exists());

        fileManager.deleteFile("teste.csv");
        assertFalse(new File("teste.csv").exists());
    }

    @Test
    public void shouldReturnFileWhenCreatingNewFile() throws Exception {
        File fileCreated = fileManager.createFile("teste.csv");
        assertNotNull(fileCreated);
        fileManager.deleteFile("teste.csv");
    }

    @Test
    public void shouldGetStreamInfo() throws Exception {
        StreamInfo streamInfo = fileManager.getStreamInfo("src/test/resources/result.png");
        assertNotNull(streamInfo);
    }

    @Test
    public void shouldReturnEmptyListIfThereIsNoHackerFiles() throws Exception {
        FileManager fileManager =  new FileManager();
        List<File>  hackers = fileManager.getAllFilesFromFolder("src/test/resources/emptyfolder");
        assertTrue(hackers.isEmpty());
    }

    @Test
    public void shouldReturnAllHackerFiles() throws Exception {
        FileManager fileManager =  new FileManager();
        List<File>  hackers = fileManager.getAllFilesFromFolder("src/test/resources");
        assertFalse(hackers.isEmpty());
    }

    @Test
    public void fileListShouldNotContainFolders() throws Exception {
        FileManager fileManager =  new FileManager();
        List<File>  hackers = fileManager.getAllFilesFromFolder("src/test/resources");

        for (File file : hackers) {
            assertTrue(file.isFile());
        }

    }

    @Test
    public void shouldOnlyReturnCSVFiles() throws Exception {
        FileManager fileManager =  new FileManager();
        List<File>  hackers = fileManager.getAllFilesFromFolder("src/test/resources");

        for (File file : hackers) {
           assertTrue(file.getName().endsWith(".csv"));
        }

    }


}
