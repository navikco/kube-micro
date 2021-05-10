package net.playdoh.algo;

import java.util.Scanner;

//TC - Linear - o(n log  n) Worst case o(n^2)
//SC - Linear - o(n)
//Purpose - Quick sort the given input array
public class QuickUnion {

    int[] nums = null;

    public QuickUnion(int length) {

        nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = i;
        }
    }

    public int root(int index) {
        while (index != nums[index]) {
            index = nums[index];
        }
        return index;
    }

    public boolean connected(int index1, int index2) {
        System.out.println("Connected : " + index1 + "<--->" + index2 + " : " + (root(index1) == root(index2)));
        return root(index1) == root(index2);
    }

    public void union(int index1, int index2) {

        int num1 = root(index1);
        int num2 = root(index2);

        nums[num1] = num2;
        System.out.println("Unioned : " + index1 + "<--->" + index2);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        QuickUnion quickFind = new QuickUnion(scanner.nextInt());

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
