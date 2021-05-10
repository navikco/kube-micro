package net.playdoh.leetcode;

import java.util.*;

public class StringPlay {

    public static void main(String[] args) {

        StringPlay stringPlay = new StringPlay();
        System.out.println("Length of Longest Substring [pwwkew] :::>>> " + stringPlay.lengthOfLongestSubstring("pwwkew"));

        System.out.println("LongestPrefix [flower,flow,flowrence, float, flood] :::>>> " + stringPlay.longestCommonPrefix(new String[]{"flower", "flow", "flowrence", "float", "flood"}));

        System.out.println("Find Needle in a Haystack  :::>>> " + stringPlay.strStr("Hello", "lo"));

        System.out.println("Last Word Length  :::>>> " + stringPlay.lengthOfLastWord(" Hello   World  "));

        System.out.println("First Recurring Character :::>>> " + stringPlay.firstRecurringChar("pqGTABabcD"));

        System.out.println("Shortest Distance :::>>> " + stringPlay.shortestDistance(new String[]{"a", "b", "c", "d", "a"}, "a", "d"));

        System.out.println("Alphanumeric String Palindrome :::>>> " + stringPlay.isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println("String Palindrome with ONE Bad Character [naamvaan] :::>>> " + stringPlay.validPalindromeDeleteOneChar("naamvaan"));
        System.out.println("String Palindrome with ONE Bad Character [deeee] :::>>> " + stringPlay.validPalindromeDeleteOneChar("deeee"));
        System.out.println("String Palindrome with ONE Bad Character [abcba] :::>>> " + stringPlay.validPalindromeDeleteOneChar("abcba"));

        System.out.println("String Anagram :::>>> " + stringPlay.isAnagram("nagAram", "Anagram"));

        System.out.println("Word Patterns Aligned[abab][dog cat dog cat] :::>>> " + stringPlay.wordPattern("abab", "dog cat dog cat"));
        System.out.println("Word Patterns Aligned[abba][dog dog dog dog] :::>>> " + stringPlay.wordPattern("abba", "dog dog dog dog"));
        System.out.println("Word Patterns Aligned[aaa][dog dog dog dog] :::>>> " + stringPlay.wordPattern("aaa", "dog dog dog dog"));

        System.out.println("Swap Vowels[leEtcOde] :::>>> " + stringPlay.reverseVowels("leEtcOde"));

        System.out.println("Add Strings[1, 9] :::>>> " + stringPlay.addStrings("1", "9"));

        System.out.println("Backspace Tracking for Two Strings[aa##a, #aaa##] :::>>> " + stringPlay.backspaceCompare("aa##a", "#aaa##"));

        System.out.println("Alien Sorted [hello,hello] : abcdefghijklmnopqrstuvwxyz :::>>> " + stringPlay.isAlienSorted(new String[]{"hello","hello"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("Alien Sorted [hello,leetcode] : hlabcdefgijkmnopqrstuvwxyz :::>>> " + stringPlay.isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println("Alien Sorted [word,world,row] : worldabcefghijkmnpqstuvxyz :::>>> " + stringPlay.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println("Alien Sorted [apple,app] : abcdefghijklmnopqrstuvwxyz :::>>> " + stringPlay.isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz"));

        System.out.println("Valid Word Abbreviation [internationalization, i12iz4n] :::>>> " + stringPlay.validWordAbbreviation("internationalization", "i12iz4n"));

        System.out.println("First Unique Char [sollywood] :::>>> " + stringPlay.firstUniqChar("sollywood"));
        System.out.println("First Unique Char [ddlj] :::>>> " + stringPlay.firstUniqChar("ddlj"));
    }

    public String firstRecurringChar(String s) {

        Set<String> chars = new HashSet<>();

       for(int i=0; i<s.length(); i++) {

            String thisS = String.valueOf(s.charAt(i));
           if(chars.contains(thisS))
               return thisS;
           else
               chars.add(thisS);
        }
       return "";
    }

    public int lengthOfLongestSubstring(String s) {

        if(s == null || s.length() == 0)
            return 0;

        int longestSubstring = 0;
        for(int i=0; i<s.length(); i++) {

            longestSubstring = Math.max(longestSubstring, checkSubstring(i, s));
            if(longestSubstring > s.length() - (i + 1))
                return longestSubstring;
        }
        return longestSubstring;
    }

    private int checkSubstring(int index, String s) {

        String substring = "";
        for(int i=index; i<s.length(); i++) {
            if(substring.indexOf(s.charAt(i)) < 0)
                substring += s.charAt(i);
            else
                break;
        }
        return substring.length();
    }

    public String longestCommonPrefix(String[] strs) {

        if (strs.length <= 0)
            return "";

        StringBuffer prefixStr = new StringBuffer();

        int shortestLength = shortestStringLength(strs);
        outer:
        for(int i=0; i<shortestLength; i++) {

            String prefix = strs[0].substring(i, i+1);
            for(int j=1; j<strs.length; j++) {

                if(!strs[j].substring(i, i+1).equals(prefix))
                    break outer;
            }
            prefixStr.append(prefix);
        }
        return prefixStr.toString();
    }

    private int shortestStringLength(String[] strs) {

        int length = Integer.MAX_VALUE;
        for(int i=0; i<strs.length; i++) {

            if(strs[i].length() < length)
                length = strs[i].length();
        }
        return length;
    }

    public int strStr(String haystack, String needle) {

        if(needle.length() == 0 || (needle.length() == 0 && haystack.length() == 0))
            return 0;

        for(int i=0; i<haystack.length(); i++) {

            char haystackChar = haystack.charAt(i);
            char needleChar = needle.charAt(0);
            int increment = 1;
            while(haystackChar == needleChar) {
                if(needle.length() == increment)
                    return i;
                if(haystack.length() == i + increment)
                    return -1;
                haystackChar = haystack.charAt(i + increment);
                needleChar = needle.charAt(increment++);
            }
        }
        return -1;
    }

    public int lengthOfLastWord(String s) {

        int lengthOfLastWord = 0;
        boolean newWord = false;
        for(int i=0; i<s.length(); i++) {

            if(newWord) {
                lengthOfLastWord = s.charAt(i) == ' ' ? lengthOfLastWord : 0;
                newWord = false;
            }
            if(s.charAt(i) == ' ')
                newWord = true;
            else
                lengthOfLastWord++;
        }

        return lengthOfLastWord;
    }

    public int shortestDistance(String[] words, String word1, String word2) {

        int word1Index = -1;
        int word2Index = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {

            if (words[i].equals(word1)) {
                word1Index = i;
            }
            if (words[i].equals(word2)) {
                word2Index = i;
            }
            if (word1Index >= 0 && word2Index >= 0) {
                if (distance > Math.abs(word1Index - word2Index)) {
                    distance = Math.abs(word1Index - word2Index);
                };
            }
        }
        return distance;
    }

    public boolean isPalindrome(String s) {

        int length = s.length();
        String sLower = s.toLowerCase();
        for (int i = 0, j = length - 1; i < length; i++, j--) {
            if (!isValid(sLower.charAt(i))) {
                j++;
                continue;
            }
            if (!isValid(sLower.charAt(j))) {
                i--;
                continue;
            }
            if (sLower.charAt(i) != sLower.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    private boolean isValid(char c) {
        if ((c >= 97 && c <= 122) || (c >= 48 && c <= 57)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validPalindromeDeleteOneChar(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {

            if (s.charAt(i) != s.charAt(j)) {
                if (i == j - 1) {
                    return true;
                }
                return palindrome(s.substring(i, j)) || palindrome(s.substring(i + 1, j + 1));
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean palindrome(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        Hashtable<Character, Integer> anagram = new Hashtable<>();
        int length = s.length();
        for (int i = 0, j = length - 1; i < length; i++, j--) {

            updateAnagram(anagram, s.charAt(i), 1);
            updateAnagram(anagram, t.charAt(i), -1);
            updateAnagram(anagram, s.charAt(j), 1);
            updateAnagram(anagram, t.charAt(j), -1);
        }
        return anagram.isEmpty();
    }

    private void updateAnagram(Hashtable<Character, Integer> anagram, char c, int move) {
        Integer charCount = anagram.get(c);
        if (charCount != null) {
            if (charCount + move == 0) {
                anagram.remove(c);
            } else {
                anagram.put(c, charCount + move);
            }
        } else {
            anagram.put(c, move);
        }
    }

    public boolean wordPattern(String pattern, String s) {

        String[] str = s.split(" ");
        if (pattern.length() != str.length) {
            return false;
        }
        Hashtable<String, Character> patterns = new Hashtable<>();

        Character patternChar;
        for (int i = 0, j = 0; i < pattern.length() && j < s.length(); i++, j++) {

            patternChar = patterns.get(str[j]);
            if (patternChar != null) {
                if (patternChar.charValue() != pattern.charAt(i)) {
                    return false;
                }
            } else {
                if (patterns.contains(pattern.charAt(i))) {
                    return false;
                } else {
                    patterns.put(str[j], pattern.charAt(i));
                }
            }
        }
        return true;
    }
    public String reverseVowels(String s) {

        String vowels = "aeiouAEIOU";
        char left, right;
        String reversed = s;
        for (int i = 0, j = s.length() - 1; i < j;) {

            left = s.charAt(i);
            right = s.charAt(j);
            if (vowels.indexOf(left) >= 0 && vowels.indexOf(right) >= 0) {
                if (left != right) {
                    reversed = s.substring(0, i) + s.charAt(j) + s.substring(i + 1, j) + s.charAt(i) + s.substring(j + 1);
                }
                i++;
                j--;
            } else {
                if (vowels.indexOf(left) < 0) {
                    i++;
                }
                if (vowels.indexOf(right) < 0) {
                    j--;
                }
            }
        }
        return reversed;
    }

    public String addStrings(String num1, String num2) {

        StringBuffer sum = new StringBuffer();
        int remainder = 0;
        int n1, n2, n;
        for (int i = num1.length() - 1, j = num2.length() - 1;;i--,j--) {

            if (i >= 0 || j >= 0) {
                n1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
                n2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
                n = n1 + n2 + remainder;
                if (n >= 10) {
                    sum.append((n % 10));
                    remainder = 1;
                } else {
                    sum.append(n);
                    remainder = 0;
                }
            } else {
                break;
            }
        }
        if (remainder > 0) {
            sum.append(remainder);
        }
        return sum.reverse().toString();
    }

    public boolean backspaceCompare(String S, String T) {

        StringBuffer sStr = new StringBuffer();
        StringBuffer tStr = new StringBuffer();
        for (int i = 0; i < S.length() || i < T.length(); i++) {
            if (i < S.length()) {
                if (S.charAt(i) == '#') {
                    deletePreviousChar(sStr);
                } else {
                    sStr.append(S.charAt(i));
                }
            }
            if (i < T.length()) {
                if (T.charAt(i) == '#') {
                    deletePreviousChar(tStr);
                } else {
                    tStr.append(T.charAt(i));
                }
            }
        }
        return sStr.toString().equals(tStr.toString());
    }

    private void deletePreviousChar(StringBuffer sb) {
        if (sb.length() == 0) {
            return;
        }
        sb.delete(sb.length() - 1, sb.length());
    }

    public boolean isAlienSorted(String[] words, String order) {

        Map<Character, Integer> alphabet = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            alphabet.put(order.charAt(i), i + 1);
        }

        int charIndex = 0;
        for (int i = 0; i < words.length - 1; i++) {

            int order1 = 0, order2 = 0;
            if (words[i].length() > charIndex) {
                order1 = alphabet.get(words[i].charAt(charIndex));
            }
            if (words[i + 1].length() > charIndex) {
                order2 = alphabet.get(words[i + 1].charAt(charIndex));
            }

            if (order1 == order2) {
                if (charIndex < words[i].length() || charIndex < words[i + 1].length()) {
                    charIndex++;
                    i--;
                }
            } else if (order1 < order2) {
                charIndex = 0;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean validWordAbbreviation(String word, String abbr) {

        if (abbr.length() > word.length()) {
            return false;
        }
        StringBuffer abbrBuffer = new StringBuffer(word);
        for (int i = 0; i < abbr.length(); i++) {
            if (abbrBuffer.length() == 0) {
                return false;
            }
            if (Character.isDigit(abbr.charAt(i)) && abbr.charAt(i) != '0') {
                int num = Character.getNumericValue(abbr.charAt(i));
                if (i < abbr.length() - 1 && Character.isDigit(abbr.charAt(i + 1))) {
                    num = Integer.parseInt(num + "" + Character.getNumericValue(abbr.charAt(i + 1)));
                    i++;
                }
                if (abbrBuffer.length() < num) {
                    return false;
                }
                abbrBuffer.delete(0, num);
            } else {
                if (abbrBuffer.charAt(0) != abbr.charAt(i)) {
                    return false;
                }
                abbrBuffer.deleteCharAt(0);
            }
        }
        return abbrBuffer.length() == 0;
    }

    public int firstUniqChar(String s) {

        int[] chars = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            chars[c - 'a'] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}