package br.com.hackerbrasileiro.webapp.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class AllHackersTest {

    @Test @Ignore
    public void shouldSaveAllHackers() throws Exception {
        AllHackers allHackers = new AllHackers();
        File file = allHackers.generateCSVFile();
        assertNotNull(file);
    }
}
