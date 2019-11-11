package com.borisovskiy.collectionsmaps.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsTimeCalculator {

    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private static CopyOnWriteArrayList<Integer> copyWriteArrayList = new CopyOnWriteArrayList<>();

    public static long calculateTime(String tag) {
        long startTime = System.nanoTime();

        switch (tag) {
            case "Adding to start in ArrayList":
                addToStart(arrayList);
                return System.nanoTime() - startTime;

            case "Adding to start in LinkedList":
                addToStart(linkedList);
                return System.nanoTime() - startTime;

            case "Adding to start in CopyWriteArrayList":
                addToStart(copyWriteArrayList);
                return System.nanoTime() - startTime;

            case "Adding to middle in ArrayList":
                addToMiddle(arrayList);
                return System.nanoTime() - startTime;

            case "Adding to middle in LinkedList":
                addToMiddle(linkedList);
                return System.nanoTime() - startTime;

            case "Adding to middle in CopyWriteArrayList":
                addToMiddle(copyWriteArrayList);
                return System.nanoTime() - startTime;

            case "Adding to end in ArrayList":
                addToEnd(arrayList);
                return System.nanoTime() - startTime;

            case "Adding to end in LinkedList":
                addToEnd(linkedList);
                return System.nanoTime() - startTime;

            case "Adding to end in CopyWriteArrayList":
                addToEnd(copyWriteArrayList);
                return System.nanoTime() - startTime;

            case "Search in ArrayList":
                searchInArrayList(5);
                return System.nanoTime() - startTime;

            case "Search in LinkedList":
                searchInLinkedList(5);
                return System.nanoTime() - startTime;

            case "Search in CopyWriteArrayList":
                searchInCopyWriteArrayList(5);
                return System.nanoTime() - startTime;

            case "Removing from start in ArrayList":
                removeFromStart(arrayList);
                return System.nanoTime() - startTime;

            case "Removing from start in LinkedList":
                removeFromStart(linkedList);
                return System.nanoTime() - startTime;

            case "Removing from start in CopyWriteArrayList":
                removeFromStart(copyWriteArrayList);
                return System.nanoTime() - startTime;

            case "Removing from middle in ArrayList":
                removeFromMiddle(arrayList);
                return System.nanoTime() - startTime;

            case "Removing from middle in LinkedList":
                removeFromMiddle(linkedList);
                return System.nanoTime() - startTime;

            case "Removing from middle in CopyWriteArrayList":
                removeFromMiddle(copyWriteArrayList);
                return System.nanoTime() - startTime;

            case "Removing from end in ArrayList":
                removeFromEnd(arrayList);
                return System.nanoTime() - startTime;

            case "Removing from end in LinkedList":
                addToEnd(linkedList);
                return System.nanoTime() - startTime;

            case "Removing from end in CopyWriteArrayList":
                addToEnd(copyWriteArrayList);
                return System.nanoTime() - startTime;

            default:
                return 0;
        }
    }

    public static void addToStart(List<Integer> list) {
        list.add(0, 7000000);
    }

    public static void addToMiddle(List<Integer> list) {
        list.add(list.size() / 2, 8000000);
    }

    public static void addToEnd(List<Integer> list) {
        list.add(9000000);
    }

    public static void searchInArrayList(int n) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == n)
                return;
        }
    }

    public static void searchInLinkedList(int n) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i) == n)
                return;
        }
    }

    public static void searchInCopyWriteArrayList(int n) {
        for (int i = 0; i < copyWriteArrayList.size(); i++) {
            if (copyWriteArrayList.get(i) == n)
                return;
        }
    }

    public static void removeFromStart(List<Integer> list) {
        list.remove(0);
    }

    public static void removeFromMiddle(List<Integer> list) {
        list.remove(list.size() / 2);
    }

    public static void removeFromEnd(List<Integer> list) {
        list.remove(list.size() - 1);
    }
}