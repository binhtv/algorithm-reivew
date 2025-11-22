package array;

import java.util.ArrayList;
import java.util.List;

public class FallingBoxes {
    private final static int[][] indices = new int[][]{
            {-1,-1},{-1,0},{-1,1},
            {0,-1},        {0,1},
            {1,-1}, {1,0}, {1,1}
    };

    private static void explode(char[][] board, List<int[]> hitPoints) {
        hitPoints.forEach(point -> {
            for (int[] index : indices) {
                int ni = point[0] + index[0];
                int nj = point[1] + index[1];
                if (ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length && board[ni][nj] == '#') {
                    board[ni][nj] = '-';
                }
            }
        });
    }

    private static void fallingBoxes(char[][] board) {
        int r = board.length;
        int c = board[0].length;
        final List<int[]> hits = new ArrayList<>();

        for (int h = 0; h < r; h++) {
            hits.clear();
            for (int i = r - 1; i > 0; i--) {
                for (int j = 0; j < c; j++) {
                    if (board[i][j] == '-' && board[i - 1][j] == '#') {//fall
                        board[i][j] = '#';
                        board[i - 1][j] = '-';
                    }
                    if (board[i][j] == '*' && board[i - 1][j] == '#') {//hit
                        board[i - 1][j] = '-';
                        hits.add(new int[]{i, j});
                    }
                }
            }
            explode(board, hits);
        }
    }

    private static void printBoard(char[][] board) {
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String... args) {
        char[][] board = new char[][]{
                {'-', '-', '-', '-', '-'},
                {'#', '#', '#', '-', '#'},
                {'#', '*', '#', '-', '-'},
                {'-', '-', '-', '*', '#'},
        };
        fallingBoxes(board);
        printBoard(board);

//        char[][] board = new char[][]{
//                {'-', '-', '-', '-', '-'},
//                {'#', '#', '#', '#', '#'},
//                {'#', '*', '#', '-', '-'},
//                {'-', '-', '-', '*', '#'},
//        };
//        fallingBoxes(board);
//        printBoard(board);
//        char[][] board = new char[][]{
//                {'-', '-', '-', '-', '#'},
//                {'#', '#', '#', '-', '-'},
//                {'#', '*', '#', '#', '-'},
//                {'-', '-', '-', '*', '#'},
//        };
//        fallingBoxes(board);
//        printBoard(board);
    }
}
