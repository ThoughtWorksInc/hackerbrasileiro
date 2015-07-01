package br.com.hackerbrasileiro.webapp.domain;


import br.com.hackerbrasileiro.webapp.domain.validator.FileValidator;
import br.com.hackerbrasileiro.webapp.util.EnvironmentVariable;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

@Component
public class Photos {

    private static final String FILE_NAME_EXTENSION = ".jpg";
    private static final String READ_MODE = "r";

    public String save(String base64Image) throws IOException {
        byte image[] = convertToByteArray(removeDataTypeInfo(base64Image));
        String fileName = generateUUID();
        File file = new File(getFilePath(fileName));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(image);
        fileOutputStream.close();
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

    private String getFilePath(String fileName) {
        String folderName = getPhotosPath().concat("/");
        FileValidator.createFolderIfDoesNotExistsFor(folderName);
        return String.format("%s%s%s", folderName, fileName, FILE_NAME_EXTENSION);
    }
}
