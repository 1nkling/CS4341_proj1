import java.util.HashSet;
import java.util.Set;

/**
 * Created by dansong on 1/28/2018.
 */



public class Board {
    private static final int DIMS = 15;
    private int[][]board = new int[DIMS][DIMS];

//    public Board(int playerColor, int opponentColor){
//
//    color = playerColor;
//    oppColor = opponentColor;
//}

    //Default constructor initializes a board full of "0"s
    public Board(){
        for(int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = 0;
    }

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

    public int getHeuristic(int color){

        return 0;
    }

    private int findSequences(int color){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == color){

                }
            }
        }
        return 0;
    }

    //Function that returns distance
    public int distFromCenter(Move m){
        return Math.abs(m.x - DIMS/2) + Math.abs(m.y - DIMS/2);
    }

    public static void main(String args[]){
        HashSet<Move> test = new HashSet<>();
        test.add(new Move(1,1));
        test.add(new Move(1,1));
        test.add(new Move(1,1));
        test.add(new Move(1,2));
        test.add(new Move(2,1));
        System.out.println(test.size());
    }
}
