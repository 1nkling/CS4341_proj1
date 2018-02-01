import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

/**
 * Created by danso on 1/28/2018.
 */
public class main {
    public static void main(String args[]){
        Player p1 = new Player("g1", 1, 2, new Board());
        while(!fileExists("g1.go")) {
            System.out.println("no .go file");
            try {
                sleep(200);
            }
            catch (Exception e){
                System.out.println("interrupted");
            }
        }
        Move m = new Move(-1,-1);
        try {
            m = readFrom("move_file", p1.name);
        }
        catch (Exception e){
            System.out.println("no file exists");
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

/*    static boolean makeMove(String path, String name) throws Exception{
        BufferedWriter br = new BufferedWriter(new FileWriter(path));
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
    }*/
}
