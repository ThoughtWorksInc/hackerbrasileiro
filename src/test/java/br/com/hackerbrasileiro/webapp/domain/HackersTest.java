package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.util.FileHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class HackersTest {

    @Mock
    private Photos photos;
    @Mock
    private FileHelper fileHelper;

    private Hackers hackers;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        hackers = new Hackers(photos, fileHelper);
    }

    @Ignore
    @Test
    public void saveHackerTest() throws Exception {
        Hacker hacker = getHacker();

        given(photos.save("photo")).willReturn("UUID");
        doNothing().when(fileHelper).createFolderIfDoesNotExistsFor(any(String.class));
        doNothing().when(fileHelper).createFileIfDoesNotExistsFor(any(String.class));
        doNothing().when(fileHelper).getFileWriter(any(String.class));

        hackers.save(hacker);

        verify(photos).save("photo");
        verify(fileHelper).createFolderIfDoesNotExistsFor(any(String.class));
        verify(fileHelper).createFileIfDoesNotExistsFor(any(String.class));
        verify(fileHelper).getFileWriter(any(String.class));
    }

    @Test
    public void getHackersDailyTest() throws Exception {
        given(fileHelper.readFile()).willReturn(getLines());

        List<Hacker> hackers = this.hackers.getHackersDaily();

        assertThat(hackers.get(0).getFirstName(), is("Fulano"));
        assertThat(hackers.get(0).getEmail(), is("f@silva.com"));
        assertThat(hackers.get(1).getLastName(), is("Costa"));
    }

    private Hacker getHacker() {
        Hacker hacker = new Hacker();
        hacker.setEmail("junior.vanin@gmail.com");
        hacker.setFirstName("Junior");
        hacker.setLastName("Vanin");
        hacker.setImageData("photo");
        return hacker;
    }

    private List<String> getLines() {
        List<String> lines = new ArrayList<>();

        lines.add("Fulano,Silva,f@silva.com");
        lines.add("Ciclano,Costa,c@silva.com");

        return lines;
    }
}
