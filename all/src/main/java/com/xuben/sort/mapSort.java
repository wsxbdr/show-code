package com.xuben.sort;

import java.util.*;

public class mapSort {

    public static void main(String[] args) {

        System.out.println(mSort("abababbXXxx"));
    }

    public static String mSort(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o2.getValue() - o1.getValue();
                } else {
                    if (o1.getKey() <= 'Z' && o2.getKey() >= 'a') {
                        return 1;
                    }
                    if (o2.getKey() <= 'Z' && o1.getKey() >= 'a') {
                        return -1;
                    }
                    return o2.getKey().compareTo(o1.getKey());
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }
}
