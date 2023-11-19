package com.label.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {

    public static String cryBySh256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        // 转换哈希值为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            hexString.append(hex);
        }
        return String.valueOf(hexString);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Sha256.cryBySh256("admin123456"));
    }
}
