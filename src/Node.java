
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
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
        depth = d; //depth remaining
        this.max = max;
        this.min = min;
        maxPlayerColor = c1;
        minPlayerColor = c2;
        isMaxPlayer = isM;
        lastMove = last;
        parent = p;
        board = b;
        children = new ArrayList<>();
    }

    static int findBestMove(Node n){
        if(n.depth == 0 || n.board.findSequences(n.maxPlayerColor, 5) > 0 || n.board.findSequences(n.minPlayerColor, 5) > 0){
            if(n.isMaxPlayer) {
                n.HeuristicVal = n.board.getHeuristic(n.maxPlayerColor) - n.board.getHeuristic(n.minPlayerColor);
                //System.out.println(n.board.getHeuristic(n.maxPlayerColor));
            }
            else
                n.HeuristicVal = n.board.getHeuristic(n.minPlayerColor) - n.board.getHeuristic(n.maxPlayerColor);
            //System.out.println(n.HeuristicVal);
            return n.HeuristicVal;
        }
        if(n.isMaxPlayer){
            int var = Integer.MIN_VALUE;
            //var = max(var, findBestMove())
            for(int i = 0; i < DIMS; i++){
                for(int j = 0; j < DIMS; j++){
                    if(!n.board.isValid(j, i))
                        continue;
                    Move lastM = new Move(j, i);
                    Board newBoard = new Board(n.board);
                    newBoard.placePiece(lastM, n.isMaxPlayer?n.maxPlayerColor:n.minPlayerColor);
                    Node newN = new Node(0, n.depth - 1, n.max, n.min, n.maxPlayerColor, n.minPlayerColor, !n.isMaxPlayer, lastM, n, newBoard);
                    n.children.add(newN);
                    var = Math.max(var, findBestMove(newN));
                    n.min = Math.max(n.min, var);
                    if(n.max <= n.min)
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
                    if(!n.board.isValid(j, i))
                        continue;
                    Move lastM = new Move(j, i);
                    Board newBoard = new Board(n.board);
                    newBoard.placePiece(lastM, n.isMaxPlayer?n.maxPlayerColor:n.minPlayerColor);
                    Node newN = new Node(0, n.depth - 1, n.max, n.min, n.maxPlayerColor, n.minPlayerColor, !n.isMaxPlayer, lastM, n, newBoard);
                    n.children.add(newN);
                    var = Math.min(var, findBestMove(newN));
                    n.max = Math.min(n.max, var);
                    if(n.max <= n.min)
                        return var;
                }
            }
            return var;
        }
    }
/*
    static int findBestMove(Node n){
        if(n.depth == 0 || n.board.findSequences(n.maxPlayerColor, 5) > 0 || n.board.findSequences(n.minPlayerColor, 5) > 0){
            if(n.isMaxPlayer)
                n.HeuristicVal = n.board.getHeuristic(n.maxPlayerColor) - n.board.getHeuristic(n.minPlayerColor);
            else
                n.HeuristicVal = n.board.getHeuristic(n.minPlayerColor) - n.board.getHeuristic(n.maxPlayerColor);
            return n.HeuristicVal;
        }
        if(n.isMaxPlayer){
            int var = Integer.MIN_VALUE;
            HashSet<Node> possibleNodeSet = getPossibleNodes(n);
            for (Node possibleNode : possibleNodeSet) {
                n.children.add(possibleNode);
                var = Math.max(var, findBestMove(possibleNode));
                n.min = Math.max(n.min, var);
                if(n.min <= n.max)
                    return var;
            }
            return var;
        }
        else{
            int var = Integer.MAX_VALUE;
            HashSet<Node> possibleNodeSet = getPossibleNodes(n);
            for (Node possibleNode : possibleNodeSet) {
                n.children.add(possibleNode);
                var = Math.max(var, findBestMove(possibleNode));
                n.min = Math.max(n.min, var);
                if(n.min <= n.max)
                    return var;
            }
            return var;
        }
    }
    
    static HashSet<Node> getPossibleNodes(Node n) {
    		HashSet<Node> possibleNodes = new HashSet<Node>();
    	
        for(int i = 0; i < DIMS; i++){
            for(int j = 0; j < DIMS; j++){
                if(!n.board.isValid(j, i))
                    continue;
                Move lastM = new Move(j, i);
                Board newBoard = new Board(n.board);
                newBoard.placePiece(lastM, n.isMaxPlayer?n.maxPlayerColor:n.minPlayerColor);
                Node newN = new Node(0, n.depth - 1, n.max, n.min, n.maxPlayerColor, n.minPlayerColor, !n.isMaxPlayer, lastM, n, newBoard);
                
                possibleNodes.add(newN);
            }
        }
        
        return possibleNodes;
    }*/
}
