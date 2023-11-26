package com.label.core.util;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTransfer {

    public static String fileToBase64(String filePath) throws IOException {
        FileInputStream inputFile = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();
                return new BASE64Encoder().encode(buffer);
            }else {
                return "";
            }
        } catch (Exception e) {
            return "";
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
        }
    }
}
