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

    public static boolean checkNewModification( String latestModificationTimeOfTransactionHistory, String latestLoginTimeOfUser) {
        boolean needUpdate = false;
        for (int i = 0; i < latestLoginTimeOfUser.length(); i++) {
            if (latestLoginTimeOfUser.charAt(i) != latestModificationTimeOfTransactionHistory.charAt(i)) {
                if(latestLoginTimeOfUser.charAt(i) < latestModificationTimeOfTransactionHistory.charAt(i)) {
                    needUpdate = true;
                    break;
                }
                if(latestLoginTimeOfUser.charAt(i) > latestModificationTimeOfTransactionHistory.charAt(i)) {
                    break;
                }
            }
        }
        return needUpdate;
    }
}
