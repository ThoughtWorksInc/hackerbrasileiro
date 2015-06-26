package br.com.hackerbrasileiro.webapp.domain;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PythonScriptTest {

    @Test
    public void shouldReturnTheCorrectCommand() throws Exception {
        final String interpreter = "interpreter";
        final String script = "script";
        final String args = "args";
        PythonScript pythonScript = new PythonScript(script, interpreter, args);
        String[] command = pythonScript.getScriptCommand();

        String[] expectedResult = new String[]{interpreter, script, args};
        assertArrayEquals(command, expectedResult);
    }

    @Test
    public void shouldExecuteScript() throws Exception {
        PythonScript pythonScript = new PythonScript("src/test/resources/test.py", "python", "custom message");
        pythonScript.execute();
    }
}