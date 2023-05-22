package com.xuben.hot100;

import java.util.HashMap;
import java.util.Map;

public class LC03 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s == null) {
            return 0;
        }

        int ans = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                start = Math.max(map.get(s.charAt(i)), start);

            }

            ans = Math.max(ans, i - start + 1);
            map.put(s.charAt(i), i + 1);
        }

        return ans;
    }
}
