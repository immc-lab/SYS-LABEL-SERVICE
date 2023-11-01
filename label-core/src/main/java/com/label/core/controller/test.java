package com.label.core.controller;

import java.io.File;
import java.io.FileInputStream;

public class test {
    public static void main(String[] args) {
        String url = "D:\\audioData\\msc.mp3";
        File file = new File(url);
        System.out.println(file.exists());

    }


}
