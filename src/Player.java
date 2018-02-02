import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.StringTokenizer;

public class Player {
    String name;
    private Node mmTree;

    Player(String name, int color, int oppColor){
        this.name = name;
        this.mmTree = new Node(0, 5, Integer.MAX_VALUE, Integer.MAX_VALUE, color, oppColor, true, null, null, new Board());

    }
    Player(String name, int color, int oppColor, Board board, int depth){
        this.name = name;
        this.mmTree = new Node(0, depth, Integer.MAX_VALUE, Integer.MAX_VALUE, color, oppColor, true, null, null, board);
    }

    boolean placePiece(Move m, boolean isMaxPlayer){
        return mmTree.board.placePiece(m.x, m.y, mmTree.isMaxPlayer?mmTree.maxPlayerColor:mmTree.minPlayerColor);
    }

    //Function that implements the gomoka game
    void makeMove(int depth){
        Node.findBestMove(mmTree);
    }


}
