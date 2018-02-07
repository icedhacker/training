package com.mycompany.anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function  boolean isAnagram(String lhs, String rhs).
 * <p>
 * An anagram is direct word switch or word play, the result of rearranging the letters of a word or phrase to
 * produce a new word or phrase, using all the original letters exactly once;
 * for example, the word ‘anagram' can be rearranged into ‘nagaram’.
 */
public class CheckAnagram {

    public boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Create the map with character counts from String s1.
        for (char ch : s1.toCharArray()) {
            if (!charCountMap.containsKey(ch)) {
                charCountMap.put(ch, 0);
            }
            charCountMap.put(ch, charCountMap.get(ch) + 1);
        }

        // Check if the same count of characters exist in String s2.
        for (char ch : s2.toCharArray()) {
            if (!charCountMap.containsKey(ch)) {
                return false;
            }
            if (charCountMap.get(ch) == 1) {
                charCountMap.remove(ch);
            } else {
                charCountMap.put(ch, charCountMap.get(ch) - 1);
            }
        }
        return charCountMap.isEmpty();
    }
}
