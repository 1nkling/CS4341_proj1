import java.util.Objects;

public class Move {
    int x;
    int y;

    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(!(obj instanceof Move))
            return false;
        Move mv = (Move) obj;
        return mv.x == x && mv.y == mv.y;
    }

}
