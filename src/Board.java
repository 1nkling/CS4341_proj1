import java.util.HashSet;
import java.util.Set;

/**
 * Created by dansong on 1/28/2018.
 */



public class Board {
    private static final int DIMS = 15;
    private int[][]board = new int[DIMS][DIMS];

    //Default constructor initializes a board full of "0"s
    public Board(){
        for(int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = 0;
    }
    public Board(Board origBoard){
        for(int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = origBoard.getValue(j, i);
    }


    int getValue(int x, int y){
        return board[y][x];
    }

    //Function that places a piece on the board if the move is legal
    boolean placePiece(int x, int y, int color){
        if(board[y][x] == 0 && x < 15 && x >= 0 && y >= 0 && y < 15) {
            board[y][x] = color;
            return true;
        }
        return false;
    }

    boolean placePiece(Move m, int color){
        if(isValid(m) && board[m.y][m.x] == 0) {
            board[m.y][m.x] = color;
            return true;
        }
        return false;
    }

    //Function that prints the current state of the game board.
    public void printBoard(){
        System.out.print("    ");
        for(int i = 0; i < board.length; i++) {
            System.out.print((char)(i + 'A') + " ");
        }
        System.out.println();
        for(int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + ": ");
            if(i < 9)
                System.out.print(" ");
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public int getHeuristic(int color){
        int heuristic = 0;
        for(int len = 2; len < 5; len++){
            //multiplies by length to value longer sequences over shorter ones
            heuristic += (findSequences(color, len)* len * len);
        }
        //if there is a sequence of length 5, the game is won.  Heavily prioritized
        if((findSequences(color, 5)) > 0)
            heuristic = Integer.MAX_VALUE;
        return heuristic;
    }

    //Note: does not return the actual number of sequences,
    //but the number of sequences + a modifier based on whether
    //or not the sequence is capped.
    int findSequences(int color, int len){
        int numSeq = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                numSeq += findLDiagonalSeq(j, i, color, len);
                numSeq += findRDiagonalSeq(j, i, color, len);
                numSeq += findHorzSeq(j, i, color, len);
                numSeq += findVertSeq(j, i, color, len);
            }
        }
        return numSeq;
    }

    //returns 0 if a right diagonal sequence was not found @ the coord,
    //returns 1 if a right diagonal sequence was found, but is capped at 1 end,
    //returns 2 if a right diagonal sequence was found and is not capped.
    private int findRDiagonalSeq(int x, int y, int color, int len) {
        int retVal = 0;
        int count = 0;
        int tempX = x;
        int tempY = y;
        //Cannot start searching for diagonal within a sequence;
        //must start at head.
        if (isValid(tempX - 1, tempY - 1)){
            if(board[tempY - 1][tempX - 1] == color)
                return retVal;
            else if(board[tempY - 1][tempX - 1] == 0){
                retVal++;
                //System.out.println("RetVal: " + retVal);
            }
        }
        while(isValid(tempX, tempY) && board[tempY][tempX] == color){
            count++;
            tempY++;
            tempX++;
            if(count == len){
/*                    System.out.println("count: " + count);
                System.out.println("x: " + tempX + "  y:" + tempY);*/
                //if a diagonal is found but the length exceeds what is desired,
                //it does not qualify.
                if(isValid(tempX, tempY + 1) && board[tempY][tempX] == color) {
                    //System.out.println("RetVal: " + retVal);
                    return 0;
                }
                else if(isValid(tempX, tempY) && board[tempY][tempX] == 0){
                    retVal++;
                }
                break;
            }
        }
        if(count != len)
            return 0;
        return retVal;
    }

    //returns 0 if a left diagonal sequence was not found @ the coord,
    //returns 1 if a left diagonal sequence was found, but is capped at 1 end,
    //returns 2 if a left diagonal sequence was found and is not capped.
    private int findLDiagonalSeq(int x, int y, int color, int len) {
        int retVal = 0;
        int count = 0;
        int tempX = x;
        int tempY = y;
        //Cannot start searching for diagonal within a sequence;
        //must start at head.
        if (isValid(tempX + 1, tempY - 1)){
            if(board[tempY - 1][tempX + 1] == color)
                return retVal;
            else if(board[tempY - 1][tempX + 1] == 0){
                retVal++;
            }
        }
        while(isValid(tempX, tempY) && board[tempY][tempX] == color){
            tempY++;
            tempX--;
            count++;
            if(count == len){
/*                    System.out.println("count: " + count);
                System.out.println("x: " + tempX + "  y:" + tempY);*/
                //if a diagonal is found but the length exceeds what is desired,
                //it does not qualify.
                if(isValid(tempX, tempY) && board[tempY][tempX] == color) {
                    return 0;
                }
                else if(isValid(tempX, tempY) && board[tempY][tempX] == 0){
                    retVal++;
                }
                break;
            }
        }
        if(count != len)
            return 0;
        return retVal;
    }

    //returns 0 if a horizontal sequence was not found @ the coord,
    //returns 1 if a horizontal sequence was found, but is capped at 1 end,
    //returns 2 if a horizontal sequence was found and is not capped.
    private int findHorzSeq(int x, int y, int color, int len){
        int retVal = 0;
        int count = 0;
        int tempX = x;
        int tempY = y;
        //Cannot start searching for diagonal within a sequence;
        //must start at head.
        if (isValid(tempX - 1, tempY)){
            if(board[tempY][tempX - 1] == color)
                return retVal;
            else if(board[tempY][tempX - 1] == 0){
                retVal++;
            }
        }
        while(isValid(tempX, tempY) && board[tempY][tempX] == color){
            //System.out.println(count);
            tempX++;
            count++;
            if(count == len) {
                //if a diagonal is found but the length exceeds what is desired,
                //it does not qualify.
                if (isValid(tempX, tempY) && board[tempY][tempX] == color)
                    return 0;
                else if (isValid(tempX, tempY) && board[tempY][tempX] == 0) {
                    retVal++;
                }
                break;
            }
        }
        //Otherwise, retVal may be 1 even if the examined coord isn't the desired color.
        //slightly inelegant; perhaps find better solution.  Add to "else if" statement?
        if(count != len)
            return 0;
        return retVal;
    }

    //returns 0 if a horizontal sequence was not found @ the coord,
    //returns 1 if a horizontal sequence was found, but is capped at 1 end,
    //returns 2 if a horizontal sequence was found and is not capped.
    private int findVertSeq(int x, int y, int color, int len){
        int retVal = 0;
        int count = 0;
        int tempX = x;
        int tempY = y;
        //Cannot start searching for diagonal within a sequence;
        //must start at head.
        if (isValid(tempX, tempY - 1)){
            if(board[tempY - 1][tempX] == color)
                return retVal;
            else if(board[tempY - 1][tempX] == 0){
                retVal++;
            }
        }
        while(isValid(tempX, tempY) && board[tempY][tempX] == color){
            //System.out.println(count);
            tempY++;
            count++;
            if(count == len) {
                //if a diagonal is found but the length exceeds what is desired,
                //it does not qualify.
                if (isValid(tempX, tempY) && board[tempY][tempX] == color)
                    return 0;
                else if (isValid(tempX, tempY) && board[tempY][tempX] == 0) {
                    retVal++;
                }
                break;
            }
        }
        //Otherwise, retVal may be 1 even if the examined coord isn't the desired color.
        //slightly inelegant; perhaps find better solution.  Add to "else if" statement?
        if(count != len)
            return 0;
        return retVal;
    }

    //Function that returns distance
    public int distFromCenter(Move m){
        return Math.abs(m.x - DIMS/2) + Math.abs(m.y - DIMS/2);
    }

    public int numNeighbors(Move m){
        int numN = 0;
        int x = m.x;
        int y = m.y;
        if(!isValid(x, y))
            return - 1;
        if(isValid(x, y - 1) && getValue(x, y - 1) != 0){
            numN++;
        }
        else if(isValid(x, y + 1) && getValue(x, y + 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y + 1) && getValue(x + 1, y + 1) != 0){
            numN++;
        }
        else if(isValid(x - 1, y + 1) && getValue(x - 1, y + 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y - 1) && getValue(x + 1, y - 1) != 0){
            numN++;
        }
        else if(isValid(x - 1, y - 1) && getValue(x - 1, y - 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y) && getValue(x + 1, y) != 0){
            numN++;
        }
        else if(isValid(x - 1, y) && getValue(x - 1, y) != 0){
            numN++;
        }
        return numN;
    }

    public int numNeighbors(int x, int y){
        int numN = 0;
        if(!isValid(x, y))
            return - 1;
        if(isValid(x, y - 1) && getValue(x, y - 1) != 0){
            numN++;
        }
        else if(isValid(x, y + 1) && getValue(x, y + 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y + 1) && getValue(x + 1, y + 1) != 0){
            numN++;
        }
        else if(isValid(x - 1, y + 1) && getValue(x - 1, y + 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y - 1) && getValue(x + 1, y - 1) != 0){
            numN++;
        }
        else if(isValid(x - 1, y - 1) && getValue(x - 1, y - 1) != 0){
            numN++;
        }
        else if(isValid(x + 1, y) && getValue(x + 1, y) != 0){
            numN++;
        }
        else if(isValid(x - 1, y) && getValue(x - 1, y) != 0){
            numN++;
        }
        return numN;
    }

    public boolean isValid(int x, int y){
        return x < DIMS && x >= 0 && y >= 0 && y < DIMS;
    }
    public boolean isValid(Move m){
        return m.x < DIMS && m.x >= 0 && m.y >= 0 && m.y < DIMS;
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
        b.placePiece(3, 2, 1);
        b.placePiece(4, 3, 1);
        b.placePiece(5, 4, 1);
        b.placePiece(6, 10, 1);
        b.placePiece(4, 10, 1);
        b.placePiece(5, 10, 1);
        b.placePiece(3, 10, 2);
        b.placePiece(0, 10, 1);
        b.placePiece(1, 10, 1);
        b.placePiece(2, 10, 1);
        b.placePiece(7, 10, 2);
        b.printBoard();
        System.out.println(b.findRDiagonalSeq(3, 2, 1, 3));
        System.out.println(b.findHorzSeq(4, 10, 1, 3));
        System.out.println(b.findHorzSeq(1, 10, 1, 3));
        System.out.println(b.findSequences(1, 3));
    }
}
