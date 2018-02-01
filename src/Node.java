import java.util.List;

public class Node {
    private static final int DIMS = 15;
    int HeuristicVal;
    int depth;
    int max;
    int min;
    int maxPlayerColor;
    int minPlayerColor;
    boolean isMaxPlayer;
    Move lastMove;
    Node parent;
    Board board;
    List<Node> children;

    Node(int h, int d, int max, int min, int c1, int c2, boolean isM, Move last, Node p, Board b){
        HeuristicVal = h;
        depth = d;
        this.max = max;
        this.min = min;
        maxPlayerColor = c1;
        minPlayerColor = c2;
        isMaxPlayer = isM;
        lastMove = last;
        parent = p;
        board = b;
    }

    int findBestMove(Node n){
        if(depth == 0 || board.findSequences(n.maxPlayerColor, 5) > 0 || board.findSequences(n.minPlayerColor, 5) > 0){
            if(n.isMaxPlayer)
                HeuristicVal = board.getHeuristic(n.maxPlayerColor) - board.getHeuristic(n.minPlayerColor);
            else
                HeuristicVal = board.getHeuristic(n.minPlayerColor) - board.getHeuristic(n.maxPlayerColor);
            return n.HeuristicVal;
        }
        if(n.isMaxPlayer){
            int var = Integer.MIN_VALUE;
            //var = max(var, findBestMove())
            for(int i = 0; i < DIMS; i++){
                for(int j = 0; j < DIMS; j++){
                    if(!board.isValid(j, i))
                        continue;
                    Move lastM = new Move(j, i);
                    Board newBoard = new Board(n.board);
                    newBoard.placePiece(lastM, isMaxPlayer?maxPlayerColor:minPlayerColor);
                    Node newN = new Node(0, n.depth + 1, n.max, n.min, n.maxPlayerColor, n.minPlayerColor, !n.isMaxPlayer, lastM, n, newBoard);
                    var = Math.max(var, findBestMove(newN));
                    n.min = Math.max(n.min, var);
                    if(n.min <= n.max)
                        return var;
                }
            }
            return var;
        }
        else{
            int var = Integer.MAX_VALUE;
            //var = max(var, findBestMove())
            for(int i = 0; i < DIMS; i++){
                for(int j = 0; j < DIMS; j++){
                    if(!board.isValid(j, i))
                        continue;
                    Move lastM = new Move(j, i);
                    Board newBoard = new Board(n.board);
                    newBoard.placePiece(lastM, isMaxPlayer?maxPlayerColor:minPlayerColor);
                    Node newN = new Node(0, n.depth + 1, n.max, n.min, n.maxPlayerColor, n.minPlayerColor, !n.isMaxPlayer, lastM, n, newBoard);
                    var = Math.max(var, findBestMove(newN));
                    n.min = Math.max(n.min, var);
                    if(n.min <= n.max)
                        return var;
                }
            }
            return var;
        }
    }



}
