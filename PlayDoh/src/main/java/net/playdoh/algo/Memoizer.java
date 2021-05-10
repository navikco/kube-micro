package net.playdoh.algo;

//TC - Recursion - o(2^k)
//SC - Linear - o(n)
//Purpose - Push Zeros to the End
public class Memoizer {

    public static void main(String[] args) {

        int[] memoizer = new int[15];
        System.out.println("Fab[15] :>>> " + fab(15, memoizer));

        System.out.println("Climbing Stairs [5] :>>> " + climbStairs(15));
    }

    private static int fab(int num, int []memoizer) {

        System.out.println("Recursion ::: " + num);

        if (num <= 1)
            return 1;

        if (memoizer[num - 1] == 0) {
//            System.out.println("Memoizer ::: " + (num - 1));
            memoizer[num - 1] = fab(num - 1, memoizer) + fab(num - 2, memoizer);
        }

        return memoizer[num - 1];
    }

    /**
     *
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top
     */
    private static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
