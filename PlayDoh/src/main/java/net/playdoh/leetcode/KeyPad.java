package net.playdoh.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

class KeyPad {

    private static Hashtable<Integer, List<String>> keypad = new Hashtable<>();

    public static void main(String[] args) {

        System.out.println(letterCombinations("234"));
    }

    public static List<String> letterCombinations(String digits) {

        List<String> results = new ArrayList<>();

        if (digits == null || digits.length() <= 0 || digits.indexOf("1") >= 0 || digits.indexOf("0") >= 0) {
            return results;
        }
        KeyPad.initializeKeyPad();

        return mapAllLetters(digits, results);
    }

    private static List<String> mapAllLetters(String digits, List<String> mapLetters) {

        if(digits.length() < 2)
            return mapLetters;

        Integer refDigit = Integer.parseInt(String.valueOf(digits.charAt(0)));
        Integer mapDigit = Integer.parseInt(String.valueOf(digits.charAt(1)));

        for (String letter : getLetters(refDigit)) {

            for (String mapLetter : getLetters(mapDigit)) {
                mapLetters.add(letter + mapLetter);
            }
        }
        return mapAllLetters(digits.substring(1), mapLetters);
    }

    private static List<String> getLetters(Integer digit) {

        return keypad.get(digit);
    }

    private static void initializeKeyPad() {

        keypad.put(new Integer(2), new ArrayList(Arrays.asList("a", "b", "c")));
        keypad.put(new Integer(3), new ArrayList(Arrays.asList("d", "e", "f")));
        keypad.put(new Integer(4), new ArrayList(Arrays.asList("g", "h", "i")));
        keypad.put(new Integer(5), new ArrayList(Arrays.asList("j", "k", "l")));
        keypad.put(new Integer(6), new ArrayList(Arrays.asList("m", "n", "o")));
        keypad.put(new Integer(7), new ArrayList(Arrays.asList("p", "q", "r", "s")));
        keypad.put(new Integer(8), new ArrayList(Arrays.asList("t", "u", "v")));
        keypad.put(new Integer(9), new ArrayList(Arrays.asList("w", "x", "y", "z")));
    }
}