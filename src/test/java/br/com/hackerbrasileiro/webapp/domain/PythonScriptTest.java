package br.com.hackerbrasileiro.webapp.domain;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PythonScriptTest {

    @Test
    public void shouldReturnTheCorrectCommand() throws Exception {
        final String interpreter = "interpreter";
        final String script = "script";
        PythonScript pythonScript = new PythonScript(script, interpreter);
        String[] command = pythonScript.getScriptCommand();

        String[] expectedResult = new String[]{interpreter, script};
        assertThat(command, is(expectedResult));
    }
}