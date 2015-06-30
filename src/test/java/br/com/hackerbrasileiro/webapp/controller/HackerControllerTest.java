package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Hackers;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

public class HackerControllerTest {

    HackerController hackerController;

    @Mock
    Hackers hackers;

    @Mock
    Photos photos;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        hackerController = new HackerController(hackers);
    }

    @Test
    public void saveTest() throws Exception {
        String homeView = hackerController.save(getHacker());

        assertThat(homeView, is("redirect:home"));
    }

    private Hacker getHacker() {
        Hacker hacker = new Hacker();
        hacker.setEmail("email");
        hacker.setFirstName("first name");
        hacker.setLastName("last name");
        hacker.setImageData("photo");

        return hacker;
    }
}
