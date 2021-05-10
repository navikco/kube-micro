package net.playdoh.algo;

import java.util.Scanner;

//TC - Linear - o(n log  n) Worst case o(n^2)
//SC - Linear - o(n)
//Purpose - Quick sort the given input array
public class QuickFind {

    int[] nums = null;

    public QuickFind(int length) {

        nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = i + 10;
        }
    }

    public boolean connected(int index1, int index2) {
        System.out.println("Connected : " + index1 + "<--->" + index2 + " : " + (nums[index1] == nums[index2]));
        return nums[index1] == nums[index2];
    }

    public void union(int index1, int index2) {

        int num1 = nums[index1];
        int num2 = nums[index2];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                nums[i] = num2;
            }
        }
        System.out.println("Unioned : " + index1 + "<--->" + index2);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        QuickFind quickFind = new QuickFind(scanner.nextInt());

        quickFind.union(2, 3);
        quickFind.union(3, 4);
        quickFind.union(4, 5);
        quickFind.union(5, 6);
        quickFind.union(6, 7);
        quickFind.union(7, 8);

        quickFind.connected(0, 1);
        quickFind.connected(1, 2);
        quickFind.connected(5, 6);
        quickFind.connected(8, 9);
        quickFind.connected(2, 8);
    }
}
