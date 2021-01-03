import java.util.ArrayList;
import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;


    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+String.valueOf(this.x)+","+String.valueOf(this.y)+")";
    }

    public  boolean precedes(Vector2d other){
        if(this.x<=other.x && this.y<=other.y){
            return  true;
        }else{
            return  false;
        }
    }

    public  boolean follows(Vector2d other){
        if(this.x>=other.x && this.y>=other.y){
            return  true;
        }else{
            return  false;
        }
    }

    public Vector2d upperRight(Vector2d other){
        int temp_x = this.x;
        int temp_y = this.y;

        if(this.x<other.x){
            temp_x=other.x;
        }

        if(this.y<other.y){
            temp_y=other.y;
        }

        return  new Vector2d(temp_x,temp_y);
    }

    public Vector2d lowerLeft(Vector2d other){
        int temp_x = this.x;
        int temp_y = this.y;

        if(this.x>other.x){
            temp_x=other.x;
        }

        if(this.y>other.y){
            temp_y=other.y;
        }

        return  new Vector2d(temp_x,temp_y);
    }

    public Vector2d add(Vector2d vector){
        int temp_x = this.x + vector.x;
        int temp_y = this.y + vector.y;

        return new Vector2d(temp_x,temp_y);
    }

    public Vector2d subtract(Vector2d vector){
        int temp_x = this.x - vector.x;
        int temp_y = this.y - vector.y;

        return new Vector2d(temp_x,temp_y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        if(that.x==this.x && that.y==this.y) return true;
        return  false;
    }

    public Vector2d opposite(){
        int temp_x = -this.x;
        int temp_y = -this.y;

        return new Vector2d(temp_x,temp_y);
    }

    public Vector2d cloneVector(){
        return new Vector2d(this.x,this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
