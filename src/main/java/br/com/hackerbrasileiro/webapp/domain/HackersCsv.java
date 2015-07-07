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

package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface HackersCsv {

    static final String NEW_LINE = "\n";
    static final String CSV_FOLDER_PATH = System.getenv().get(EnvironmentVariable.FILE_PATH).concat("/");

    String formatLine(Hacker hacker, String UUID);

    void addLine(String line) throws IOException;

    List<Hacker> getHackersDaily() throws IOException;

    File generateCsvFile() throws IOException;
}
