package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Photos;
import br.com.hackerbrasileiro.webapp.domain.RandomHacker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmailControllerTest {

    EmailController emailController;

    @Mock
    Photos photos;
    @Mock
    RandomHacker randomHacker;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        emailController = new EmailController(photos, randomHacker);

        when(photos.getImageAsByteArray(any(String.class))).thenReturn(new byte[1]);
    }

    @Test
    public void emailViewTest() throws Exception {
        ModelAndView adminView = emailController.createAdminView();
        assertThat(adminView.getViewName(), is("admin"));
    }
}
