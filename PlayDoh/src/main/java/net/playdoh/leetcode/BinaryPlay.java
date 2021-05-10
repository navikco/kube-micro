package net.playdoh.leetcode;

public class BinaryPlay {

    public static void main(String[] args) {

        BinaryPlay binaryPlay = new BinaryPlay();
        System.out.println("Binary Add Two Strings [11111, 111111] :::>>> " + binaryPlay.addBinary1("11111", "111111"));
    }

    public String addBinary1(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    public String addBinary(String a, String b) {

        if (a == null || b == null)
            return "";
        if (a.length() == 0)
            return b;
        if (b.length() == 0)
            return a;

        String binarySum = "", aStr = "", bStr = "";
        int remainder = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {

            if (i < 0)
                aStr = "0";
            else
                aStr = String.valueOf(a.charAt(i));
            if (j < 0)
                bStr = "0";
            else
                bStr = String.valueOf(b.charAt(j));
            int sum = addBinaryNumbers(aStr, bStr, remainder);
            if (sum == 0) {
                binarySum = "0" + binarySum;
            } else if (sum == 1) {
                binarySum = "1" + binarySum;
                remainder = 0;
            } else if (sum == 2) {
                binarySum = "0" + binarySum;
                remainder = 1;
            } else if (sum == 3) {
                binarySum = "1" + binarySum;
                remainder = 1;
            }
        }
        if (remainder == 1)
            binarySum = "1" + binarySum;
        return binarySum;
    }

    private int addBinaryNumbers(String aStr, String bStr, int remainder) {

        int aNum = Integer.parseInt(aStr);
        int bNum = Integer.parseInt(bStr);

        return aNum + bNum + remainder;
    }
}