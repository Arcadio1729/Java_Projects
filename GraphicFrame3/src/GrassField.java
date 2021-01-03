import java.util.*;


public class GrassField extends AbstractWorldMap {

    private int GrassFieldAmount;

    //private List<Grass> GrassPoints;

    private int Max_X;
    private int Max_Y;



    public GrassField(int GrassFieldAmount){
        this.GrassFieldAmount=GrassFieldAmount;
        //this.GrassPoints = new ArrayList<Grass>();

        this.GrassPoints = new LinkedHashMap<>();
        this.Animals = new LinkedHashMap<>();
        //this.Animals = new ArrayList<Animal>();

        this.maxAnimalEnergy=0;
        this.Max_X=0;
        this.Max_Y=0;
    }

    public void setGrassFields(){
        double LowerLimit=0;
        double UpperLimit=Math.sqrt(GrassFieldAmount*10);
        int TempX=0;
        int TempY=0;
        Vector2d TempPosition = new Vector2d(0,0);
        for(int i=0;i<this.GrassFieldAmount;i++){
            TempX=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
            TempY=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
            TempPosition=new Vector2d(TempX,TempY);
            if(!this.isOccupied(TempPosition)){
                this.GrassPoints.put(TempPosition,  new Grass(TempPosition));
                if(TempX>this.Max_X) this.Max_X=TempX;
                if(TempY>this.Max_Y) this.Max_Y=TempX;
            }else{
                i--;
            }
        }
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(this.Max_X+1,this.Max_Y+1));
    }

    public boolean place(Animal animal){
        if(isOccupied(animal.getPosition())){
            return false;
        }else{
            int animal_x=animal.getPosition().x;
            int animal_y=animal.getPosition().y;
            if(animal_x>this.Max_X) this.Max_X=animal_x;
            if(animal_y>this.Max_Y) this.Max_Y=animal_y;
            this.Animals.put(new Vector2d(animal_x,animal_y),animal);
        }
        return true;
    }
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position)!=null){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.x>=0 && position.y>=0) {
            if(!isOccupied(position)){
                return true;
            }else{
                if(objectAt(position).getClass().toString()=="Animal"){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public Object objectAt(Vector2d position) {
        for(Vector2d v : this.GrassPoints.keySet()){
            if(v.hashCode()==position.hashCode()){
                return this.GrassPoints.get(v);
            }
        }

        for(Vector2d v : this.Animals.keySet()){
            if(v.hashCode()==position.hashCode()){
                return this.Animals.get(v);
            }
        }
        return null;
    }

    public int getMaxAnimalEnergy(){
        return this.maxAnimalEnergy;
    }
}

