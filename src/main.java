

/**
 * Created by danso on 1/28/2018.
 */
public class main {
    public static void main(String args[]){
        Board p1 = new Board();
        try{
            //p1.makeMove();
        }catch (Exception e){
            System.out.println("exception handle");
        }
        p1.printBoard();
    }
}
