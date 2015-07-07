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