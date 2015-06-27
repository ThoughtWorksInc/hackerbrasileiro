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

public class AdminRepresentationControllerTest {

    AdminController adminController;

    @Mock
    Photos photos;
    @Mock
    RandomHacker randomHacker;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        adminController = new AdminController(photos, randomHacker);

        when(photos.getImageAsByteArray(any(String.class))).thenReturn(new byte[1]);
    }

    @Test
    public void shouldShowAdminViewTest() throws Exception {
        ModelAndView adminView = adminController.createAdminView();
        assertThat(adminView.getViewName(), is("admin"));
    }

    @Test
    public void runScriptTest() throws Exception {
        byte[] result = adminController.runScript();
        assertThat(result.length > 0, is(true));
    }
}
