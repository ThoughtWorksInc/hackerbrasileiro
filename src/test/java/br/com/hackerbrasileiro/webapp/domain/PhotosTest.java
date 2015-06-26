package br.com.hackerbrasileiro.webapp.domain;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class PhotosTest {

    private static final String FILE_NAME_EXTENSION = ".png";
    private static final String FOLDER_NAME = "src/main/resources/photos/";
    private static final String BASE64_IMAGE = "src/test/resources/photoTest.txt";

    @Test
    public void shouldSavePNGImageFromBase64Test() throws Exception {
        Photos photos = new Photos();
        String content = new Scanner(new File(BASE64_IMAGE)).next();
        String fileName = photos.save(content);

        File file = new File(FOLDER_NAME.concat(fileName).concat(FILE_NAME_EXTENSION));
        boolean fileExists = file.exists() && !file.isDirectory();

        assertTrue(fileExists);

        file.delete();
    }

    @Test
    public void shouldGetImageAsByteArrayTest() throws Exception {
        Photos photos = new Photos();
        byte[] imageByteArray = photos.getImageAsByteArray("src/test/resources/result.png");
        assertTrue(imageByteArray.length > 0);
    }
}
