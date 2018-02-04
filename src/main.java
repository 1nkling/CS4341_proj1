import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

/**
 * Created by danso on 1/28/2018.
 */
public class main {
    private static final int depth = 1;
    private static final String PLAYER1 = "g1";
    private static final String PLAYER2 = "g2";
    public static void main(String args[]){
        Player p1 = new Player(PLAYER1, 1, 2, new Board());
        Move m;
        System.out.println("Player: " + p1.name);
        while(!fileExists("end_game")) {
            if(fileExists(PLAYER1 + ".go")) {
                try {
                    m = readFrom("move_file", PLAYER2);
                    if(m == null)
                        continue;
                } catch (Exception e) {
                    System.out.println("no file exists");
                    try {
                        sleep(250);
                    } catch (Exception e2) {
                        System.out.println("sleep interrupted");
                    }
                    continue;
                }
                if (m.y == -1) {
                    System.out.println("First move!");
                    m.x = 7;
                    m.y = 7;
                    try {
                        makeMove("move_file", m, p1.name);
                        p1.placePiece(m, true);
                        p1.clearChildren();
                        try {
                            sleep(250);
                        } catch (Exception e2) {
                            System.out.println("sleep interrupted");
                        }
                    } catch (IOException e) {
                        System.out.println("move_file doesn't exist");
                    }
                    continue;
                }
                if(!p1.placePiece(m, false)){
                    System.out.println("illegal move");
                }
                p1.buildTree(depth, true);
                try {
                    p1.printBoard();
                    Move bestM = p1.getBestMove();
                    System.out.println("x: " + bestM.x + "  y: " + bestM.y);
                    makeMove("move_file", bestM, p1.name);
                    p1.placePiece(bestM, true);
                    p1.clearChildren();
                    try {
                        sleep(550);
                    } catch (Exception e2) {
                        System.out.println("sleep interrupted");
                    }
                } catch (IOException e) {
                    System.out.println("move_file doesn't exist");
                }
            }
        }
    }

    //Function that returns true if a file exists in the current path;
    //returns false otherwise.  Probably should just move this to main function.
    static boolean fileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory())
            return true;
        return false;
    }

    static Move readFrom(String path, String name) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String input = br.readLine();
        if(input == null){
            return new Move(-1, -1);
        }
        //System.out.println("Input: " + input);
        StringTokenizer st = new StringTokenizer(input);
        String targetN = st.nextToken();
        if(!targetN.equals(name))
            return null;
        // - 1 because their axes start at "1"
        int x = st.nextToken().charAt(0) - 'a';
        int y = Integer.parseInt(st.nextToken()) - 1;
        return new Move(x, y);
    }

    static boolean makeMove(String path, Move move, String name) throws IOException{
        BufferedWriter br = new BufferedWriter(new FileWriter(path));
        System.out.println("Player: " + name + " | x: " + move.x + " | y: " + move.y);
        char x = (char)(move.x + 'A');
        String y = String.valueOf(move.y + 1);
        String output = name + " " + x + " " + y;
        System.out.println("Output: " + output);
        br.write(output);
        br.close();
        return true;
    }

}
