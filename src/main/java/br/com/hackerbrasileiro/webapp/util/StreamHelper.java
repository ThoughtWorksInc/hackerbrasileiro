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


import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static lombok.AccessLevel.*;

@FieldDefaults(level = PRIVATE)
public class StreamHelper {
    @Getter InputStream inputStream;
    @Getter String fileName;
    @Getter long fileSize;

    public StreamHelper(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        File file = new File(fileName);
        inputStream = new FileInputStream(file);
        this.fileSize = file.length();
    }
}
