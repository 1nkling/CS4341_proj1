import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.StringTokenizer;

public class Player {
    private int color;
    private int oppColor;
    private Board board;
    //private Node mmTable;

    Player(int color, int oppColor){
        this.color = color;
        this.oppColor = oppColor;
        this.board = new Board();
    }
    Player(int color, int oppColor, Board board){
        this.color = color;
        this.oppColor = oppColor;
        this.board = new Board(board);
    }

    //Function that implements the gomoka game
    void makeMove() throws Exception{
        int x;
        int y;

        //unlikely that this functionality remains here; makes more sense to be in main
        if(!fileExists("g1.go")) {
            System.out.println("no .go file");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader("move_file"));
        String input = br.readLine();
        System.out.println(input);

        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        // - 1 because their axes start at "1"
        x = st.nextToken().charAt(0) - '@' - 1;
        y = st.nextToken().charAt(0) - '0' - 1;

        board.placePiece(x,y, oppColor);
    }

/*    private Move findBestMove(Board board, int depth, int max, int min, boolean thisPlayer){
        if(depth == 0 || board.findSequences(color, 5) > 0){

        }
    }*/

    //Function that returns true if a file exists in the current path;
    //returns false otherwise.  Probably should just move this to main function.
    boolean fileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory())
            return true;
        return false;
    }

}
