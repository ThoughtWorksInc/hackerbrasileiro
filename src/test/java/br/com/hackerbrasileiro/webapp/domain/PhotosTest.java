package br.com.hackerbrasileiro.webapp.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PhotosTest {

    private static final String FILE_NAME_EXTENSION = ".png";
    private static final String FOLDER_NAME = "photos/";
    private static final String BASE64_IMAGE = "src/test/resources/photoTest.txt";

    // TODO: test is sharing the same folder of the application. We have to fix it.
    @Test @Ignore
    public void shouldSavePNGImageFromBase64Test() throws Exception {
        Photos photos = new Photos();
        String content = new Scanner(new File(BASE64_IMAGE)).next();
        String fileName = photos.save(content);

        File file = new File(FOLDER_NAME.concat(fileName).concat(FILE_NAME_EXTENSION));
        boolean fileExists = file.exists() && !file.isDirectory();

        assertThat(fileExists, is(true));

        file.delete();
    }

    @Test
    public void shouldGetImageAsByteArrayTest() throws Exception {
        Photos photos = new Photos();
        byte[] imageByteArray = photos.getImageAsByteArray("src/test/resources/result.png");
        assertThat(imageByteArray.length > 0, is(true));
    }
}
