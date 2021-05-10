package net.playdoh.algo;

import java.util.Arrays;

//TC - Linear - o(n^n)
//SC - Linear - o(n)
//Purpose - Bubble sort the given input array
public class BubbleSort {

    public static void main(String[] args) {

        int[] array1 = {39, 16, 41, 4, 1, 18, 22, 8, 27, 23, 31};
        int lastUnsorted = array1.length - 1;
        boolean isSorted = false;
        while (!isSorted) {

            isSorted = true;
            System.out.println("Outer...");
            for (int j = 0; j < lastUnsorted; j++) {

                System.out.println("Inner [" + j + "]...");
                if (array1[j] > array1[j + 1]) {

                    swap(array1, j, j + 1);
                    isSorted = false;
                }
            }
            lastUnsorted--;
        }
        System.out.println("Sorted Array ::: " + Arrays.toString(array1));
    }

    private static void swap(int[] array1, int index1, int index2) {
        array1[index1] += array1[index2];
        array1[index2] = array1[index1] - array1[index2];
        array1[index1] = array1[index1] - array1[index2];
    }
}
