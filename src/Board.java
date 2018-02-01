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

    private int findSequences(int color, int len){
        int numSeq = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int currI = i;
                int currJ = j;
                Move head = new Move(j, i);
                Move tail;
                int count = 0;
                while(board[currI][currJ] == 0 && isValid(i, j)) {
                    if (board[i][j] != color) {
                        break;
                    }
                    if (i > 0) {
                        if (board[i - 1][j] == color) {

                        }
                    }
                }
            }
        }
        return 0;
    }

    //returns 0 if a diagonal was not found @ the coord,
    //returns 1 if a diagonal was found, but is capped at 1 end,
    //returns 2 if a diagonal was found and is not capped.
    private int findDiagonalSeq(int x, int y, int color, int len){
        int retVal = 0;
        int count = 0;
        int tempX = x;
        int tempY = y;
        //Cannot start searching for diagonal within a sequence;
        //must start at head.
        if(isValid(tempX - 1, tempY - 1) && board[tempY - 1][tempX - 1] == color)
            return retVal;
        else if(board[tempY - 1][tempX - 1] == 0){
            retVal++;
        }
        while(isValid(tempX, tempY)){
            if(board[tempY][tempX] == color){
                tempY++;
                tempX++;
                count++;
                if(count == len){
                    //if a diagonal is found but the length exceeds what is desired,
                    //it does not qualify.
                    if(isValid(tempX + 1, tempY + 1) && board[tempY + 1][tempX + 1] == color)
                        return 0;
                    else if(board[tempY + 1][tempX + 1] == 0){
                        retVal++;
                    }
                }
            }
        }
        return retVal;
    }

    //Function that returns distance
    public int distFromCenter(Move m){
        return Math.abs(m.x - DIMS/2) + Math.abs(m.y - DIMS/2);
    }

    public boolean isValid(int x, int y){
        return x < DIMS && x >= 0 && y >= 0 && y < DIMS;
    }

    public static void main(String args[]){
        Board b = new Board();
        HashSet<Move> test = new HashSet<>();
        test.add(new Move(1,1));
        test.add(new Move(1,1));
        test.add(new Move(1,1));
        test.add(new Move(1,2));
        test.add(new Move(2,1));
        System.out.println(test.size());
        b.printBoard();
    }
}
