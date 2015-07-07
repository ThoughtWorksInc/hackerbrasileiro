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

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ScriptExecutor {
    String script;
    String interpreter;

    public void execute() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(getScriptCommand()).waitFor();
    }

    public String[] getScriptCommand() {
        String[] command = new String[2];
        command[0] = interpreter;
        command[1] = script;
        return command;
    }

    public static String getScriptPath() {
        return System.getenv().get(EnvironmentVariable.FACEMORPHER_PATH);
    }
}
