package net.playdoh.algo;

import java.util.Arrays;

//TC - Linear - o(n log  n) Worst case o(n^2)
//SC - Linear - o(n)
//Purpose - Quick sort the given input array
public class MergeSort {

    public static void main(String[] args) {

        int[] array1 = {39, 16, 41, 4, 1, 18, 22, 8, 27, 23, 31};

        quickSort(array1, 0, array1.length - 1);

        System.out.println("Sorted Array ::: " + Arrays.toString(array1));
    }

    private static void quickSort(int []array, int left, int right) {
        if(left >= right) {
            return;
        }
        int index = partition(array, left, right);
        quickSort(array, left, index - 1);
        quickSort(array, index, right);
    }

    private static int partition(int[] array, int left, int right) {

        int pivot = array[left];

        while (left <= right) {

            while(array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] array1, int index1, int index2) {
        int temp = array1[index1];
        array1[index1] = array1[index2];
        array1[index2] = temp;
    }
}
