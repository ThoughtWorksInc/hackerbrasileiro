package br.com.hackerbrasileiro.webapp.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PythonScript {
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
}
