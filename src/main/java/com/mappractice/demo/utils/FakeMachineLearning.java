package com.mappractice.demo.utils;

import java.util.HashMap;

public class FakeMachineLearning {

    public static HashMap<String, Long> machine = new HashMap<>();

    public static void setting(){
        machine.put("아몰랑", 0L);

        machine.put("BBQ", 1L);
        machine.put("BHC", 1L);
        machine.put("도미노피자", 1L);
        machine.put("PizzaHut", 1L);

        machine.put("메가박스", 2L);
        machine.put("CGV", 2L);
        machine.put("롯데시네마", 2L);

        machine.put("스타벅스", 3L);

        machine.put("gs25", 4L);
        machine.put("cu", 4L);
        machine.put("미니스톱", 4L);
        machine.put("이마트", 4L);

        machine.put("sk텔레콤", 5L);
        machine.put("LGU+", 5L);
        machine.put("KT", 5L);

        machine.put("11번가", 6L);
        machine.put("타몬", 6L);
        machine.put("쿠팡", 6L);
        machine.put("위메프", 6L);

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
