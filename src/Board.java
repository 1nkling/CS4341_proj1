
/**
 * Created by dansong on 1/28/2018.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Board {
    private int[][]board = new int[15][15];

//    public Board(int playerColor, int opponentColor){
//
//    color = playerColor;
//    oppColor = opponentColor;
//}

    //Function that places a piece on the board if the move is legal
    boolean placePiece(int x, int y, int color){
        if(board[y][x] == 0 && x < 15 && x >= 0 && y >= 0 && y < 15) {
            board[y - 1][x - 1] = color;
            return true;
        }
        return false;
    }

    //Function that prints the current state of the game board.
    public void printBoard(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
}
