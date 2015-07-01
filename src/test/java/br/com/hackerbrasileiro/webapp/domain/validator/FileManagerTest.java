package br.com.hackerbrasileiro.webapp.domain.validator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {

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
}
