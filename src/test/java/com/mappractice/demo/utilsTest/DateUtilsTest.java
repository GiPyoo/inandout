package com.mappractice.demo.utilsTest;

import com.mappractice.demo.utils.DateUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    @Test
    public void checkNewModificationTest(){
        String LatestLoginTimeOfUser = "20190628095011";
        String LatestModificationTimeOfTransactionHistory = "20190628095200";
        assertEquals(true, DateUtils.checkNewModification(LatestLoginTimeOfUser,LatestModificationTimeOfTransactionHistory));
    }

}
