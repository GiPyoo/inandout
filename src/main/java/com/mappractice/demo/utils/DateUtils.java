package com.mappractice.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {
    }

    public static String getFolderPathByDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(formatter) + "/";
    }

    public static boolean checkNewModification(String LatestLoginTimeOfUser, String LatestModificationTimeOfTransactionHistory) {
        boolean isModified = false;
        for (int i = 0; i < LatestLoginTimeOfUser.length(); i++) {
            if (LatestLoginTimeOfUser.charAt(i) < LatestModificationTimeOfTransactionHistory.charAt(i)) {
                isModified = true;
                break;
            }
        }
        return isModified;
    }
}
