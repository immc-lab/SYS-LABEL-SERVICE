package com.label.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetLocalDataTime {
    public static String  getTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
