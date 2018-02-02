import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.StringTokenizer;

public class Player {
    String name;
    private int color;
    private int oppColor;
    private int searchDepth;
    private Board board;
    private Node mmTree;

    Player(String name, int color, int oppColor){
        this.name = name;
        this.color = color;
        this.oppColor = oppColor;
        this.board = new Board();
    }
    Player(String name, int color, int oppColor, Board board){
        this.name = name;
        this.color = color;
        this.oppColor = oppColor;
        this.board = new Board(board);
    }

    //Function that implements the gomoka game
    void makeMove() throws Exception{
        int x;
        int y;

        //unlikely that this functionality remains here; makes more sense to be in main

        //board.placePiece(x,y, oppColor);
    }


}
