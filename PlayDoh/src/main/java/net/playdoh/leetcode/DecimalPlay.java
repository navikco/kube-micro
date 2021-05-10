package net.playdoh.leetcode;

public class DecimalPlay {

    public static void main(String[] args) {

        System.out.println("Proportion of +ve, -ve and 0 Numbers [0, 1, 1, -8, 0, 9] :::>>> ");
        DecimalPlay.plusMinus(new int[] {0, 1, 1, -8, 0, 9});
        System.out.println("Proportion of +ve, -ve and 0 Numbers [1, 1, -8, -9] :::>>> ");
        DecimalPlay.plusMinus(new int[] {1, 1, -8, -9});
    }

    private static void plusMinus(int[] arr) {

        int positive = 0, negative = 0, zero = 0;
        for (int num : arr) {
            if (num < 0) {
                negative++;
            } else if (num > 0) {
                positive++;
            } else {
                zero++;
            }
        }
        System.out.println("Positive : " + String.format("%.6f", ((positive >  0) ? (double) positive / arr.length : 0.000000)));
        System.out.println("Negative : " + String.format("%.6f", ((negative >  0) ? (double) negative / arr.length : 0.000000)));
        System.out.println("Zero : " + String.format("%.6f", ((zero >  0) ? zero / (double) arr.length : 0.000000)));
    }
}