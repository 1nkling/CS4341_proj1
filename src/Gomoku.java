/**
 * Created by dansong on 1/28/2018.
 */

import java.io.File;

public class Gomoku {
    private int color;
    private int[][]board = new int[15][15];

    public Gomoku(int pColor){
        color = pColor;
    }

    //Function that implements the gomoka game
    void makeMove(){
        if(!fileExists("g1.go"))
            return;

    }

    private void setColor(int color){
        this.color = color;
    }

    //Function that places a piece on the board if the move is legal
    private boolean placePiece(int x, int y){
        if(board[x][y] == 0 && x < 15 && y < 15) {
            board[x][y] = this.color;
            return true;
        }
        return false;
    }

    //Function that returns true if a file exists in the current path;
    //returns false otherwise.
    boolean fileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory())
            return true;
        return false;
    }

    //Function that prints the current state of the game board.
    void printBoard(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
}
