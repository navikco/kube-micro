package net.playdoh.leetcode;

import java.util.*;

public class NumberPlay {

    public static void main(String[] args) {

        NumberPlay numberPlay = new NumberPlay();
        System.out.println("Reverse of Integer [-450] :::>>> " + numberPlay.reverse(-450));

        System.out.println("Palindrom String? [45054] :::>>> " + numberPlay.isPalindrome(45054));

        System.out.println("Roman to Integer [MCMXCIV] :::>>> " + numberPlay.romanToInt("MCMXCIV"));

        System.out.println("ClosestSum :::>>> " + numberPlay.closestSum(new int[]{-1, 3, 8, 2, 9, 5}, new int[]{4, 1, 2, 10, 5, 20}, 24));

//        System.out.println("Sub Array Sum [12, 1, 61, 5, 9, 2] :::>>> " + numberPlay.subarraySum(new int[]{12, 1, 61, 5, 9, 2}, 24));

        System.out.println("MissingNumber :::>>> " + numberPlay.missingNumber(new int[]{2, 3, 0, 6, 1, 4}));

        int[] a = new int[]{3, 2, 1};
        numberPlay.multiplyOthers(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println("Multiply Others :::>>> " + a[i]);
        }

        a = new int[]{7, 3, 4, 8, 1, 12, 2, 9, 5};
        int num = 108;
        int[] multiplication = numberPlay.exactMultiplication(a, num);
        System.out.println("Find Exact Multiplication :::>>> " + num + " == " + multiplication[0] + " * " + multiplication[1]);

        System.out.println("Square Root of [10] :::>>> " + numberPlay.mySqrt(10));

        System.out.println("is Perfect Square [10] :::>>> " + numberPlay.isPerfectSquare(10));
        System.out.println("is Perfect Square [25] :::>>> " + numberPlay.isPerfectSquare(25));

        System.out.println("Add Digits UNTIL Single Number [2348600] :::>>> " + numberPlay.addDigits(2348600));
    }

    //1,2,3,4,5
    public void multiplyOthers(int[] a) {

        int prev = 1;

        for (int i = 0; i < a.length; i++) {

            int aNum = a[i];
            int mult = 1;
            for (int j = i + 1; j < a.length; j++) {
                mult *= a[j];
            }
            a[i] = mult * prev;
            prev *= aNum;
        }
    }

    public int[] exactMultiplication(int[] a, int num) {

        int[] multiplication = new int[]{};

        Set<Integer> nums = new HashSet<>();

        for (int i = 0; i < a.length; i++) {

            System.out.println(a[i] + " : " + a[i]);
            if (num % a[i] == 0)
                if (nums.contains(num / a[i]))
                    return new int[]{num / a[i], a[i]};
                else
                    nums.add(a[i]);
        }
        return multiplication;
    }

    public int closestSum(int[] a, int[] b, int num) {

        Arrays.sort(a);
        Arrays.sort(b);

        int closestSum = a[0] + b[0];
        int closestDiff = Math.abs(a[0] + b[0] - num);

        int i = 0, j = b.length - 1;
        while (i < a.length && j >= 0) {

            int currentDiff = a[i] + b[j] - num;
            if (Math.abs(currentDiff) < closestDiff) {

                closestSum = a[i] + b[j];
                closestDiff = Math.abs(currentDiff);
            }

            if (currentDiff == 0)
                return currentDiff;
            else if (currentDiff < 0)
                i++;
            else
                j--;
        }
        return closestSum;
    }

    public int reverse(int x) {

        boolean negative = false;
        if (x < 0)
            negative = true;
        String numberStr = Math.abs(x) + "";
        StringBuffer reverseNumberStr = new StringBuffer();
        for (int i = numberStr.length() - 1; i >= 0; i--) {

            reverseNumberStr.append(numberStr.charAt(i));
        }

        return Integer.parseInt((negative ? "-" : "") + reverseNumberStr.toString());
    }

    public boolean isPalindrome(int x) {

        if (x < 0)
            return false;

        String numberStr = Integer.toString(x);
        if (numberStr.length() == 1) {
            return true;
        }
        for (int i = 0; i < numberStr.length(); i++) {

            if (numberStr.charAt(i) != numberStr.charAt(numberStr.length() - (i + 1)))
                return false;
        }
        return true;
    }

    public int romanToInt(String s) {

        Hashtable<String, Integer> romanset = new Hashtable<>();
        romanset.put("I", new Integer(1));
        romanset.put("V", new Integer(5));
        romanset.put("X", new Integer(10));
        romanset.put("L", new Integer(50));
        romanset.put("C", new Integer(100));
        romanset.put("D", new Integer(500));
        romanset.put("M", new Integer(1000));
        romanset.put("IV", new Integer(4));
        romanset.put("IX", new Integer(9));
        romanset.put("XL", new Integer(40));
        romanset.put("XC", new Integer(90));
        romanset.put("CD", new Integer(400));
        romanset.put("CM", new Integer(900));

        int number = 0;
        for (int i = s.length() - 1; i >= 0; i--) {

            if (i > 0 && romanset.get(s.substring(i - 1, i + 1)) != null) {

                number += romanset.get(s.substring(i - 1, i + 1));
                i--;
            } else {

                number += romanset.get(s.substring(i, i + 1));
            }
        }
        return number;
    }

    public int missingNumber(int[] nums) {

        int missing = 0;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {

            total += nums.length - i;
            missing += nums[i];
        }
        return total - missing;
    }

    public int mySqrt(int x) {

        if (x < 2) {
            return x;
        }

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {

            pivot = left + (right - left) / 2;
            num = (long) pivot * pivot;
            if (num > x) {
                right = pivot - 1;
            } else if (num < x) {
                left = pivot + 1;
            } else {
                return pivot;
            }
        }
        return right;
    }

    public boolean isPerfectSquare(int num) {

        if (num < 2) {
            return true;
        }
        long left = 2;
        long right = num / 2;
        long pivot;
        while (left <= right) {

            pivot = left + (right - left) / 2;

            if (pivot * pivot > num) {
                right = pivot - 1;
            } else if (pivot * pivot < num) {
                left = pivot + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public int addDigits(int num) {

        String s = num + "";
        while (num >= 10) {
            int result = 0;
            int lo = 0;
            int hi = s.length() - 1;
            while (lo <= hi) {
                if (lo < hi) {
                    result += Character.getNumericValue(s.charAt(lo)) + Character.getNumericValue(s.charAt(hi));
                } else {
                    result += Character.getNumericValue(s.charAt(lo));
                }
                lo++;
                hi--;
            }
            if (result < 10) {
                return result;
            }
            s = result + "";
        }
        return num;
    }
//
//    public int[] subarraySum(int[] nums, int k) {
//
//        Arrays.sort(nums);
//
//        for (int i = 0; i < nums.length; i++) {
//
//
//        }
//
//        int count = 0;
//        for (int start = 0; start < nums.length; start++) {
//            for (int end = start + 1; end <= nums.length; end++) {
//                int sum = 0;
//                for (int i = start; i < end; i++)
//                    sum += nums[i];
//                if (sum == k)
//                    count++;
//            }
//        }
//        return count;
//    }
}