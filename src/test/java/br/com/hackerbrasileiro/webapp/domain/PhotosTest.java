package br.com.hackerbrasileiro.webapp.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PhotosTest {

    //TODO: implement more tests

    @Test
    public void shouldGetImageAsByteArrayTest() throws Exception {
        Photos photos = new Photos();
        byte[] imageByteArray = photos.getImageAsByteArray("src/test/resources/result.png");
        assertThat(imageByteArray.length > 0, is(true));
    }
}
