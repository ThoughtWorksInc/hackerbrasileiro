package br.com.hackerbrasileiro.webapp.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class FileHelperTest {

    @Mock StreamHelper streamHelper;

    FileHelper fileHelper;

    @Before
    public void setUp() throws Exception {
        fileHelper = new FileHelper();
    }

    @Test
    public void createAndDeleteFileTest() throws Exception {
        fileHelper.createFileIfDoesNotExistsFor("test.csv");
        assertThat(new File("test.csv").exists(), is(true));

        fileHelper.delete("test.csv");
        assertThat(new File("test.csv").exists(), is(false));
    }

    @Test
    public void getStreamForTest() throws Exception {
        StreamHelper streamHelper = fileHelper.getStreamFor("src/test/resources/result.png");
        assertThat(streamHelper, notNullValue());
    }

    @Test
    public void getCsvFilesFromTest() throws Exception {
        List<File> hackers = fileHelper.getFilesFrom("src/test/resources");

        assertThat(hackers.size(), is(1));
        assertThat(hackers.get(0).getName().endsWith(".csv"), is(true));
    }
}
