package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hacker;
import br.com.hackerbrasileiro.webapp.domain.Hackers;
import br.com.hackerbrasileiro.webapp.domain.Photos;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class HomeControllerTest {

    HomeController homeController;

    @Mock
    Hackers hackers;

    @Mock
    Photos photos;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        homeController = new HomeController(hackers);
    }

    @Test
    public void shouldShowHomeViewTest() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();
        ModelAndView homeView = homeController.createHome(model);

        assertThat(homeView.getViewName(), is("home"));
    }

    @Test
    public void greetingSubmitTest() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();
        ModelAndView homeView = homeController.greetingSubmit(getHacker(), model);

        assertThat(homeView.getViewName(), is("thankyou"));
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