package br.com.hackerbrasileiro.webapp.domain;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HackerTest {

    @Test
    public void buildWithTest() {
        String line = "firstName,lastName,email";
        Hacker hacker = Hacker.buildWith(line);

        assertThat(hacker.getFirstName(), is("firstName"));
        assertThat(hacker.getLastName(), is("lastName"));
        assertThat(hacker.getEmail(), is("email"));
    }

}
