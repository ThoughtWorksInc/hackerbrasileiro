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

package br.com.hackerbrasileiro.webapp.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RandomHackerTest {

    RandomHacker randomHacker;

    @Mock
    Hackers hackers;

    @Before
    public void setUp() throws IOException {
        initMocks(this);

        randomHacker = new RandomHacker(hackers);

        List<Hacker> hackers = getHackerList();
        when(this.hackers.getHackersDaily()).thenReturn(hackers);
    }

    @Test
    public void getHackersDailyTest() throws IOException {
        randomHacker.getRandomHacker();
        verify(hackers, times(1)).getHackersDaily();
    }

    @Test
    public void getCorrectEmailTest() throws IOException {
        Hacker hacker = getHacker();
        Hacker randomHacker = this.randomHacker.getRandomHacker();

        assertThat(randomHacker.getEmail(), is(hacker.getEmail()));
    }

    @Test
    public void notThrowExceptionWithEmptyListTest() throws IOException {
        when(hackers.getHackersDaily()).thenReturn(null);
        randomHacker.getRandomHacker();
    }

    private List<Hacker> getHackerList() {
        List<Hacker> hackers = new ArrayList<>();
        hackers.add(getHacker());
        return hackers;
    }

    private Hacker getHacker() {
        Hacker hacker = new Hacker();
        hacker.setFirstName("My name");
        hacker.setLastName("My last name");
        hacker.setEmail("email@email.com");

        return hacker;
    }
}
