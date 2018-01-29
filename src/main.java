

/**
 * Created by danso on 1/28/2018.
 */
public class main {
    public static void main(String args[]){
        Gomoku p1 = new Gomoku(1);
        p1.printBoard();
        try{
            p1.makeMove();
        }catch (Exception e){
            System.out.println("exception handle");
        }

    }
}
