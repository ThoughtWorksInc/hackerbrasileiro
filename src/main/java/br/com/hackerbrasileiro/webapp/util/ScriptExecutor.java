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