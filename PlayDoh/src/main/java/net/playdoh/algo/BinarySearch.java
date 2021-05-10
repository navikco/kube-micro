package net.playdoh.algo;

import java.util.Arrays;
import java.util.Scanner;

//TC - Linear - o(log n)
//SC - Linear - o(n)
//Purpose - Binary Search for given element
public class BinarySearch {

    public static void main(String[] args) {

        int[] array1 = {2, 4, 9, 10, 13, 18, 23, 25, 31, 37, 38, 39, 44, 48, 51, 55, 59, 60, 62, 65, 70, 77};

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Element to search ::: ");
        int element = scanner.nextInt();

        int left = 0;
        int right = array1.length;
        while (left <= right) {

            int mid = left + ((right - left) / 2);
            System.out.println("Looping for Mid Point :::>>> " + mid + " @ Value :::>>> " + array1[mid]);
            if (array1[mid] == element) {
                System.out.print("Found your Element at Index[" + mid + "] ::: " + array1[mid]);
                return;
            } else if (element > array1[mid]) {
                left = mid + 1;
            } else
                right = mid - 1;
        }
        System.out.print("Your Element [" + element + "] doesn't exist in Input Array ::: " + Arrays.toString(array1));
    }
}
