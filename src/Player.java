
public class Player {
    String name;
    private Node mmTree;

    Player(String name, int color, int oppColor){
        this.name = name;
        //this.mmTree = new Node(0, 5, Integer.MAX_VALUE, Integer.MIN_VALUE, color, oppColor, true, null, null, new Board());
        this.mmTree = new Node(0, color, oppColor, null,  new Board());

    }
    Player(String name, int color, int oppColor, Board board){
        this.name = name;
        //this.mmTree = new Node(0, depth, Integer.MAX_VALUE, Integer.MIN_VALUE, color, oppColor, true, null, null, board);
        this.mmTree = new Node(0, color, oppColor, null, board);
    }

    boolean placePiece(Move m, boolean isMaxPlayer){
        return mmTree.board.placePiece(m.x, m.y, isMaxPlayer?mmTree.maxPlayerColor:mmTree.minPlayerColor);
    }

    //Function that implements the gomoka game
    void buildTree(int depth, boolean isMaxPlayer){
        //System.out.println(Node.findBestMove(mmTree));
        Node.findBestMove(mmTree, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, isMaxPlayer);
    }

    void printBoard(){
        mmTree.board.printBoard();
    }

    Move getBestMove(){
        int maxHeuristic = Integer.MIN_VALUE;
        //this should not be necessary; make Node comparable by HeuristicValue!
        for(Node child : mmTree.children){
            //System.out.println(child.max);
            if(child.HeuristicVal > maxHeuristic){
                maxHeuristic = child.HeuristicVal;
            }
        }
        for(Node child : mmTree.children){
            if(child.HeuristicVal == maxHeuristic){
                return child.lastMove;
            }
        }
        return null;
    }

    void clearChildren(){
        this.mmTree.clearChildren();
    }

    //void findBestMove

    public static void main(String args[]){
        Board b = new Board();
/*        b.placePiece(3, 2, 1);
        b.placePiece(4, 3, 1);
        b.placePiece(5, 4, 1);
        b.placePiece(6, 10, 1);
        b.placePiece(4, 10, 1);
        b.placePiece(5, 10, 1);
        b.placePiece(3, 10, 2);
        b.placePiece(0, 10, 1);
        b.placePiece(1, 10, 1);
        b.placePiece(2, 10, 1);
        b.placePiece(7, 10, 2);*/
        b.placePiece(7, 5, 2);
        b.placePiece(6, 6, 2);
        b.placePiece(5, 7, 2);
        b.placePiece(6, 7, 1);
        b.placePiece(7, 7, 1);
        b.placePiece(7, 6, 1);
/*        b.placePiece(7, 7, 2);
        b.placePiece(8, 6, 2);
        b.placePiece(6, 6, 1);*/
        b.printBoard();
        Player p = new Player("p1", 1, 2, b);
        p.buildTree(1, true);
        Move m = p.getBestMove();
        System.out.println(m.x + " " + m.y);
        b.placePiece(m, 1);
        b.printBoard();
        //System.out.println(b.getHeuristic(1));
    }

}
