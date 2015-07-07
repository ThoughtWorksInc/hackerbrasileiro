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

        assertThat(homeView, is("redirect:/"));
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
