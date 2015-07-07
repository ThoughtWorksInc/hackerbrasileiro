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

import br.com.hackerbrasileiro.webapp.util.FileHelper;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class Hackers implements HackersCsv {

    Photos photos;
    FileHelper fileHelper;

    @Autowired
    public Hackers(Photos photos, FileHelper fileHelper) {
        this.photos = photos;
        this.fileHelper = fileHelper;
    }

    public void save(Hacker hacker) throws IOException {
        String UUID = photos.save(hacker.getImageData());
        String line = formatLine(hacker, UUID);
        addLine(line);
    }

    @Override
    public String formatLine(Hacker hacker, String UUID) {
        return String.format("%s %s", hacker.toString(), NEW_LINE);
    }

    public void addLine(String line) throws IOException {
        fileHelper.createFolderIfDoesNotExistsFor(FileHelper.getFilePath());
        fileHelper.createFileIfDoesNotExistsFor(FileHelper.getName());
        fileHelper.getFileWriter(line);
    }

    @Override
    public List<Hacker> getHackersDaily() throws IOException {
        List<String> lines = fileHelper.readFile();

        Stream<Hacker> hackerStream = lines.stream().map(line -> Hacker.buildWith(line));
        return hackerStream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public File generateCsvFile() throws IOException {
        String filePath = FileHelper.getFilePath();
        String fileName = filePath.concat(FileHelper.ALL_HACKERS_FILE);

        fileHelper.createFolderIfDoesNotExistsFor(filePath);
        fileHelper.delete(fileName);

        List<File> hackersFiles =  fileHelper.getFilesFrom(filePath);

        fileHelper.createFileIfDoesNotExistsFor(fileName);

        fileHelper.merge(hackersFiles);
        return new File(fileName);
    }
}
