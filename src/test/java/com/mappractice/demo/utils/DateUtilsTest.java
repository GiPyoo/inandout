package com.mappractice.demo.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void checkNewModificationTest() {
        String LatestLoginTimeOfUser = "20190628095011";
        String LatestModificationTimeOfTransactionHistory = "20190628095200";
        assertEquals(true, DateUtils.checkNewModification(LatestModificationTimeOfTransactionHistory, LatestLoginTimeOfUser));
    }


}