import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameField extends AbstractWorldMap{

    private int GrassFieldAmount;

    private int Max_X;
    private int Max_Y;

    private LinkedHashMap<Vector2d,PlayingCell> Cells;
    private ArrayList<Vector2d> grassPoints; //for tests

    private int elementSize;
    public GameField(int GrassFieldAmount, int Map_Size,int elementSize){
        this.GrassFieldAmount=GrassFieldAmount;
        this.Cells=new LinkedHashMap<>();

        this.maxAnimalEnergy=0;
        this.Max_X=Map_Size;
        this.Max_Y=Map_Size;
        this.mapSize=Map_Size;

        this.newAnimalPositions=new ArrayList<Animal>();
        this.grassPoints=new ArrayList<Vector2d>();
        this.setPlayingCells();

        this.elementSize=elementSize;
    }

    private void setPlayingCells(){
        for(int i=0;i<this.Max_X;i++){
            for(int j=0;j<this.Max_Y;j++){
                PlayingCell pc = new PlayingCell(i,j,this);
                this.Cells.put(new Vector2d(i,j),pc);
            }
        }
    }
    public void setGrassFields(){
        double LowerLimit=0;
        double UpperLimit=Math.sqrt(this.mapSize*this.elementSize)-1;
        int TempX=0;
        int TempY=0;
        Vector2d TempPosition = new Vector2d(0,0);
        for(int i=0;i<this.GrassFieldAmount;i++){
            TempX=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
            TempY=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
            TempPosition=new Vector2d(TempX,TempY);
                //if(!this.grassAt(TempPosition)) {
                    this.Cells.get(TempPosition).addGrass(new Grass(TempPosition));
                    this.grassPoints.add(TempPosition);
                //}
        }
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(this.Max_X+1,this.Max_Y+1));
    }

    public boolean place(Animal animal){
        Vector2d animalPosition=animal.getPosition();

            int animal_x=animal.getPosition().x;
            int animal_y=animal.getPosition().y;
            if(!(animal_x>this.Max_X) || (animal_y>this.Max_Y)){
                animal.setObserver(this.Cells.get(animalPosition));
                this.Cells.get(animalPosition).addAnimal(animal);
                return true;
            }
            return false;
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

    public boolean grassAt(Vector2d position){
        if(this.Cells.get(position).getGrass()!=null) return true;

        return false;
    }

    public Object objectAt(Vector2d position){
        for(Vector2d v : this.Cells.keySet()){
            if(v.hashCode()==position.hashCode()){
                return this.Cells.get(v);
            }
        }
        return null;
    }

    public ArrayList<Animal> animalsAt(Vector2d position) {
        for(Vector2d v : this.Cells.keySet()){
            if(v.equals(position)){
                return this.Cells.get(v).getAnimals();
            }
        }
        return null;
    }

    public int getMaxAnimalEnergy(){
        return this.maxAnimalEnergy;
    }

    public void updateMap(){
        Vector2d currentAnimalPosition;

        for(int i=0;i<this.newAnimalPositions.size();i++){
            Animal currentAnimal=this.newAnimalPositions.get(i);
            PlayingCell currentCell;
            currentAnimalPosition = currentAnimal.getPosition();
            if(this.grassPoints.contains(currentAnimalPosition)){

            }
            currentCell = this.Cells.get(currentAnimalPosition);
            currentCell.addAnimal(currentAnimal);
            this.Cells.put(currentAnimalPosition,currentCell);
        }

        this.newAnimalPositions.clear();

        for(int i=0;i<this.Max_X;i++){
            for(int j=0;j<this.Max_Y;j++){
                this.Cells.get(new Vector2d(i,j)).eatGrass();
                this.Cells.get(new Vector2d(i,j)).reproduce();
                this.Cells.get(new Vector2d(i,j)).useEnergyAnimal(-1);
                this.Cells.get(new Vector2d(i,j)).animalDies();
            }
        }

        int TempX=0;
        int TempY=0;
        double LowerLimit=0;
        double UpperLimit=Math.sqrt(this.mapSize*this.elementSize);

        TempX=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
        TempY=(int)(LowerLimit + (int)(Math.random() * ((UpperLimit - LowerLimit) + 1)));
        Vector2d TempPosition=new Vector2d(TempX,TempY);

        this.Cells.get(TempPosition).addGrass(new Grass(TempPosition));
        this.grassPoints.add(TempPosition);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return true;
    }

    public boolean cellExists(Vector2d position){
        if(this.Cells.containsKey(position)) return true;
        return false;
    }

    public ArrayList<Vector2d> getGrassPoints(){
        return this.grassPoints;
    }

    public void moveAnimals(){
        for(int i=0;i<this.Max_X;i++){
            for(int j=0;j<this.Max_Y;j++){
                PlayingCell pc = this.Cells.get(new Vector2d(i,j));
                pc.moveAnimals();
                this.Cells.put(new Vector2d(i,j),pc);
            }
        }
    }

    public PlayingCell getCell(Vector2d v){
        return this.Cells.get(v);
    }

    public HashMap<Vector2d,PlayingCell> getPlayingCells(){
        return this.Cells;
    }
}
