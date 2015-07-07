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
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class Photos {

    private static final String READ_MODE = "r";

    FileHelper fileHelper;

    @Autowired
    public Photos(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    public String save(String base64Image) throws IOException {
        byte image[] = convertToByteArray(removeDataTypeInfo(base64Image));
        String fileName = generateUUID();
        fileHelper.createFor(image, fileName);
        return fileName;
    }

    public byte[] getImageAsByteArray(String imagePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(imagePath, READ_MODE);
        byte[] result = new byte[(int) file.length()];
        file.readFully(result);
        return result;
    }

    public Integer countNumberOfFilesInFolder(String folder) {
        try {
            return new File(folder).list().length;
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getPhotosPath() {
        return System.getenv().get(EnvironmentVariable.PHOTO_PATH);
    }

    private byte[] convertToByteArray(String text) {
        return Base64.decodeBase64(text);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private String removeDataTypeInfo(String text) {
        return text.substring(text.lastIndexOf(',')+1);
    }
}
