package com.branthragan.vending_machine.demo;

import java.util.HashMap;
import java.util.Map;

public class DemoInventory {
    public static Map<String, Integer> buildInventory() {
        Map<String, Integer> temp = new HashMap<>();
        temp.put("Beverage1", 4);
        temp.put("Beverage2", 4);
        temp.put("Beverage3", 4);
        temp.put("Beverage4", 4);
        temp.put("Beverage5", 4);

        return temp;
    }
}
