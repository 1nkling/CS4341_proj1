import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

/**
 * Created by danso on 1/28/2018.
 */
public class main {
    public static void main(String args[]){
        Player p1 = new Player("g1", 1, 2, new Board(), 5);
        Move m = new Move(-1,-1);
        while(!fileExists("end_game")) {
            try {
                m = readFrom("move_file", p1.name);
            }
            catch (Exception e){
                System.out.println("no file exists");
                try {
                    sleep(150);
                }
                catch (Exception e2){
                    System.out.println("sleep interrupted");
                }
                continue;
            }
            p1.placePiece(m, false);
            p1.buildTree();
            try {
                makeMove("move_file", p1.getBestMove(), p1.name);
            }
            catch (IOException e){
                System.out.println("move_file doesn't exist");
            }
            break;
        }

        System.out.println(m.x + " " + m.y);
/*        try{
            if(!fileExists("g1.go")) {
                System.out.println("no .go file");
                return;
            }
        }catch (Exception e){
            System.out.println("exception handle");
        }*/
        //p1.printBoard();
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
        System.out.println(input);

        StringTokenizer st = new StringTokenizer(input);
        String targetN = st.nextToken();
        if(!targetN.equals(name))
            return null;
        // - 1 because their axes start at "1"
        int x = st.nextToken().charAt(0) - '@' - 1;
        int y = st.nextToken().charAt(0) - '0' - 1;
        return new Move(x, y);
    }

    static boolean makeMove(String path, Move move, String name) throws IOException{
        BufferedWriter br = new BufferedWriter(new FileWriter(path));
        char x = (char)(move.x + '@' + 1);
        char y = (char)(move.y + '0' + 1);
        String output = name + " " + x + " " + y;
        br.write(output);

        return true;
    }

}
