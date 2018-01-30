
/**
 * Created by dansong on 1/28/2018.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Gomoku {
    private int color;
    private int oppColor;
    private int[][]board = new int[15][15];

    public Gomoku(int playerColor, int opponentColor){

        color = playerColor;
        oppColor = opponentColor;
    }

    //Function that implements the gomoka game
    void makeMove() throws Exception{
        int x;
        int y;
        if(!fileExists("g1.go")) {
            System.out.println("no .go file");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader("move_file"));
        String input = br.readLine();
        System.out.println(input);

        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        x = st.nextToken().charAt(0) - '@';
        y = st.nextToken().charAt(0) - '0';

        placePiece(x,y, oppColor);
    }

    private void setColor(int color){
        this.color = color;
    }

    //Function that places a piece on the board if the move is legal
    private boolean placePiece(int x, int y, int color){
        if(board[y][x] == 0 && x < 15 && x >= 0 && y >= 0 && y < 15) {
            board[y - 1][x - 1] = color;
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
    public void printBoard(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
}
