package com.borisovskiy.collectionsmaps.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsTimeCalculator {

    private static Map<Integer, Integer> treeMap = new TreeMap<>();
    private static Map<Integer, Integer> hashMap = new HashMap<>();

    public static long calculateTime(String tag) {
        long startTime = System.nanoTime();

        switch (tag) {
            case "Adding new in TreeMap":
                addNewPair(treeMap);
                return System.nanoTime() - startTime;

            case "Adding new in HashMap":
                addNewPair(hashMap);
                return System.nanoTime() - startTime;

            case "Search by key in TreeMap":
                searchByKeyTreeMap(5);
                return System.nanoTime() - startTime;

            case "Search by key in HashMap":
                searchByKeyHashMap(5);
                return System.nanoTime() - startTime;

            case "Removing in TreeMap":
                removePair(treeMap);
                return System.nanoTime() - startTime;

            case "Removing in HashMap":
                removePair(hashMap);
                return System.nanoTime() - startTime;

            default:
                return 0;
        }
    }

    private static void addNewPair(Map<Integer, Integer> map) {
        map.put(map.keySet().size(), map.keySet().size());
    }

    private static void searchByKeyTreeMap(int n) {
        for (Integer integer : treeMap.keySet()) {
            if (integer == n) return;
        }
    }

    private static void searchByKeyHashMap(int n) {
        for (Integer integer : treeMap.keySet()) {
            if (integer == n) return;
        }
    }

    private static void removePair(Map<Integer, Integer> map) {
        map.remove(map.keySet().size() / 2);
    }
}