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

    public static boolean checkNewModification( String LatestModificationTimeOfTransactionHistory, String LatestLoginTimeOfUser) {
        boolean needUpdate = false;
        for (int i = 0; i < LatestLoginTimeOfUser.length(); i++) {
            if (LatestLoginTimeOfUser.charAt(i) < LatestModificationTimeOfTransactionHistory.charAt(i)) {
                needUpdate = true;
                break;
            }
        }
        return needUpdate;
    }
}
