package br.com.hackerbrasileiro.webapp.domain;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PythonScriptTest {

    @Test
    public void shouldReturnTheCorrectCommand() throws Exception {
        final String interpreter = "interpreter";
        final String script = "script";
        final String args = "args";
        PythonScript pythonScript = new PythonScript(script, interpreter, args);
        String[] command = pythonScript.getScriptCommand();

        String[] expectedResult = new String[]{interpreter, script, args};
        assertThat(command, is(expectedResult));
    }
}