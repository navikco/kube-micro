package net.playdoh.algo;

//TC - Linear - o(n)
//SC - Linear - o(n)
//Purpose - Find matching elements from three sorted & unique arrays
public class MatchingElements {

    public static void main(String[] args) {

        int[] array1 = {4, 9, 13, 18, 23, 31};
        int[] array2 = {1, 3, 4, 10, 13, 19, 23, 24, 32};
        int[] array3 = {2, 6, 7, 8, 13, 15, 23};

        int a1 = 0, a2 = 0, a3 = 0;

        while (a1 < array1.length && a2 < array2.length && a3 < array3.length) {

            System.out.println("array1 & array2 & array3");
            if (array1[a1] == array2[a2] && array2[a2] == array3[a3]) {
                System.out.println("Matching Element at array1[" + a1 + "] & array2[" + a2 + "] ::: " + array1[a1]);
            }
            if (array1[a1] > array2[a2]) {
                a2++;
            } else if (array2[a2] > array3[a3]){
                a3++;
            } else {
                a1++;
            }
        }
    }
}
