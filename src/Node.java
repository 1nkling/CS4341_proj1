import java.util.List;

public class Node {
    int HeuristicVal;
    int depth;
    int max;
    int min;
    int color;
    boolean isMaxPlayer;
    Move lastMove;
    Node parent;
    Board board;
    List<Node> children;

    Node(int h, int d, int max, int min, int c, boolean isM, Move last, Node p, Board b){
        HeuristicVal = h;
        depth = d;
        this.max = max;
        this.min = min;
        color = c;
        isMaxPlayer = isM;
        lastMove = last;
        parent = p;
        board = b;
    }

    int findBestMove(Node n){
        if(depth == 0 || board.findSequences(color, 5) > 0){
            return n.HeuristicVal;
        }
        if(n.isMaxPlayer){
            int var = Integer.MIN_VALUE;
            //var = max(var, findBestMove())
        }
        return 0;
    }



}
