package net.playdoh.algo;

//TC - Recursion - o(2^k)
//SC - Linear - o(n)
//Purpose - Push Zeros to the End
public class Recursion {

    public static void main(String[] args) {

        System.out.println("Input ::: 7 :>>> " + fab(6));
    }

    private static int fab(int num) {

        System.out.println("Recursion ::: " + num);
        if (num <= 1)
            return 1;
        else
            return fab(num - 1) + fab(num - 2);
    }
}
