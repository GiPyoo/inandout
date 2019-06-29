package com.mappractice.demo.utils;

import java.util.HashMap;

public class FakeMachineLearning {

    public static HashMap<String, Long> machine = new HashMap<>();

    public static void setting(){
        machine.put("아몰랑", 0L);
        machine.put("월급", 0L);
        machine.put("아모스", 0L);
        machine.put("소담채", 0L);

        machine.put("한국맥도날드", 1L);
        machine.put("교촌치킨", 1L);
        machine.put("도미노피자", 1L);
        machine.put("미스터피자", 1L);
        machine.put("역전할머니맥주",1L);
        machine.put("혼끼", 1L);

        machine.put("메가박스", 2L);
        machine.put("CGV", 2L);
        machine.put("구글플레이", 2L);
        machine.put("블랑PC", 2L);
        machine.put("크로스핏용봉", 2L);
        machine.put("플랫폼PC", 2L);

        machine.put("스타벅스", 3L);
        machine.put("카페베네", 3L);

        machine.put("gs25", 4L);
        machine.put("CU역삼성정보점", 4L);
        machine.put("씨제이올리브", 4L);
        machine.put("알파문구", 4L);

        machine.put("sk텔레콤", 5L);
        machine.put("LGU+", 5L);
        machine.put("KT", 5L);
        machine.put("이비법인택시", 5L);
        machine.put("아람약국", 5L);

        machine.put("11번가", 6L);
        machine.put("타몬", 6L);
        machine.put("쿠팡", 6L);
        machine.put("위메프", 6L);
        machine.put("NIKE충장", 6L);

        machine.put("메가스터디", 7L);
        machine.put("이투스", 7L);
    }

    public static long findMachineLearning(String key){
        if(machine.isEmpty()){
            setting();
        }
        if(machine.containsKey(key)){
            return machine.get(key);
        }
        return -1;
    }
}
