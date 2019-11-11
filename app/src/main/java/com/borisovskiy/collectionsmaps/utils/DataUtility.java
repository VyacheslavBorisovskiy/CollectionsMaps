package com.borisovskiy.collectionsmaps.utils;

import java.util.List;
import java.util.Map;

public class DataUtility {

    public static void fillCollection(List<Integer> list, int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            list.add(i);
        }
    }

    public static void fillMap(Map<Integer, Integer> maps, int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            maps.put(i, i);
        }
    }
}