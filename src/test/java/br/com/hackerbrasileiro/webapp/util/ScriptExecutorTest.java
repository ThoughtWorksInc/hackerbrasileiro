package br.com.hackerbrasileiro.webapp.util;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ScriptExecutorTest {

    @Test
    public void returnCorrectCommandTest() throws Exception {
        final String interpreter = "interpreter";
        final String script = "script";
        ScriptExecutor scriptExecutor = new ScriptExecutor(script, interpreter);
        String[] command = scriptExecutor.getScriptCommand();

        String[] expectedResult = new String[]{interpreter, script};
        assertThat(command, is(expectedResult));
    }
}