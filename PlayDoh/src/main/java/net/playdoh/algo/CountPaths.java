package net.playdoh.algo;

//TC - Recursion - o(2^k)
//SC - Linear - o(n)
//Purpose - Count all possible paths from Top Left to Bottom Right corner of multi-dimensional array
public class CountPaths {

    private static int pathCheckCount;

    public static void main(String[] args) {

        boolean[][] board = {
                {true, true, false, true, true, true},
                {false, true, true, false, true, true},
                {false, true, false, true, false, true},
                {true, true, true, true, true, true},
                {true, false, true, false, true, true},
                {false, true, true, true, true, true}
        };

        int[][] memoizer = new int[board.length][board.length];

        System.out.println("Nos. of Paths From Top Left to Bottom Right :::>>> " + countPaths(board, 0, 0, memoizer));
    }

    private static int countPaths(boolean[][] board, int row, int column, int[][] memoizer) {

        System.out.println("Path Check[" + row + "][" + column + "] : " + pathCheckCount++);

        if (row >= board.length || column >= board.length)
            return 0;
        else if (row == board.length - 1 && column == board.length - 1)
            return 1;
        else if (board[row][column] == false)
            return 0;
        if (memoizer[row][column] == 0) {
            memoizer[row][column] = countPaths(board, row + 1, column, memoizer) + countPaths(board, row, column + 1, memoizer);
        }
        return memoizer[row][column];
    }
}
