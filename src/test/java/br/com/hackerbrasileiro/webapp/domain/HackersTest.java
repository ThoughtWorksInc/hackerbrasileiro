package br.com.hackerbrasileiro.webapp.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class HackersTest {

    @Mock
    private Photos photos;
    private Hackers hackers;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        hackers = new Hackers(photos);
    }

    @Test
    public void shouldSaveHackerTest() throws Exception {
        Hacker hacker = getHacker();

        given(photos.save("photo")).willReturn("UUID");
        hackers.save(hacker);

        verify(photos).save("photo");
    }

    @Test
    public void shouldGetDailyListTest() throws Exception {
        Hacker hacker = getHacker();

        given(photos.save("photo")).willReturn("UUID");
        hackers.save(hacker);

        List<Hacker> hackersList = hackers.getHackersDaily();
        Hacker lastHacker = hackersList.get(hackersList.size() - 1);

        assertThat(lastHacker.getEmail(), is(hacker.getEmail()));
    }

    private Hacker getHacker() {
        Hacker hacker = new Hacker();
        hacker.setEmail("junior.vanin@gmail.com");
        hacker.setFirstName("Junior");
        hacker.setLastName("Vanin");
        hacker.setImageData("photo");
        return hacker;
    }
}
