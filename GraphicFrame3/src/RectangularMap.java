
import java.util.*;
import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {

    private int width;
    private int height;

    RectangularMap(int width,int height){
        if(width>=0 && height>=0){
            this.width=width;
            this.height=height;
            this.Animals = new LinkedHashMap<>();
            //this.Animals=new ArrayList<Animal>();
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }


    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Vector2d v : this.Animals.keySet()){
            if(v.hashCode()==position.hashCode()){
                return this.Animals.get(v);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(width,height));
    }

    /*public boolean place(Animal animal){
        if(isOccupied(animal.getPosition())){
            return false;
        }else{
            int animal_x=animal.getPosition().x;
            int animal_y=animal.getPosition().y;
            if(animal_x>this.width) this.width=animal_x;
            if(animal_y>this.height) this.height=animal_y;
            this.Animals.put(new Vector2d(animal_x,animal_y),animal);
        }
        return true;
    }*/
}
