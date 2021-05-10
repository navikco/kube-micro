package net.playdoh.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//TC - Linear - o(n)
//SC - Linear - o(1)
//Purpose - Remove Duplicate from sorted array and return new Length
public class DuplicateElements {

    public static void main(String[] args) {

        int index = 1;
        int[] array1 = {4, 4, 9, 13, 18, 23, 23, 31};

        for (int i = 0; i < array1.length - 1; i++) {

            if (array1[i] != array1[i + 1]) {
                System.out.println("Duplicate Element ::: " + array1[i] + " at array1[" + i + "] & [" + (i + 1) + "]");
                array1[index++] = array1[i + 1];
            }
        }
        System.out.println("Unique Array [" + index + "] ::: " + Arrays.toString(array1));

        System.out.println("Lonely Integer in Array [1, 3, 4, 1, 1, 2, 3, 2, 2, 3] ::: " + lonelyinteger(new int[]{1, 2, 3, 4, 3, 2, 1}));
        System.out.println("Lonely Integer in Array [1, 3, 4, 1, 1, 2, 3, 2, 2, 3] ::: " + lonelyinteger(new int[]{1, 3, 4, 1, 1, 2, 3, 2, 2, 3}));
    }

    // Complete the lonelyinteger function below.
    static int lonelyinteger(int[] a) {

        Map<Integer, Integer> nums = new HashMap<>();

        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            if (a[i] == a[j]) {
                if (nums.containsKey(a[i])) {
                    nums.replace(a[i], nums.get(a[i]).intValue() + 2);
                } else {
                    nums.put(a[i], (i == j) ? 1 : 2);
                }
            } else {
                addToNums(nums, a[i], 1);
                addToNums(nums, a[j], 1);
            }
            i++;
            j--;
        }

        for(int num : nums.keySet()) {

            if (nums.get(num) == 1) {
                return num;
            }
        }
        return 0;
    }

    private static void addToNums(Map<Integer, Integer> nums, int num, int increment) {
        if (nums.containsKey(num)) {
            nums.replace(num, nums.get(num).intValue() + increment);
        } else {
            nums.put(num, increment);
        }
    }
}
