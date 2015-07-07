/*
Copyright (C) 2015  ThoughtWorks, Inc.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
