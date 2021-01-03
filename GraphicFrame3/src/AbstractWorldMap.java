import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class AbstractWorldMap implements IWorldMap {
    //protected List<Animal> Animals;
    protected LinkedHashMap<Vector2d,Animal> Animals;
    protected LinkedHashMap<Vector2d,Grass> GrassPoints;
    protected int maxAnimalEnergy;
    public abstract boolean place(Animal animal);
    protected int mapSize;
    protected ArrayList<Animal> newAnimalPositions;

    public void run(MoveDirection[] directions) {
        int animal_index=0;
        int animal_count=this.Animals.size();

        Animal tempAnimal;
        for(int i=0;i< directions.length;i++){
            tempAnimal=this.Animals.entrySet().iterator().next().getValue();
            tempAnimal.move();
        }
    }

    @Override
    public abstract boolean isOccupied(Vector2d position);


    @Override
    public abstract String toString();

    public Map<Vector2d, Animal> getAnimals(){
        return this.Animals;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal tempAnimal = this.Animals.get(oldPosition);
        this.Animals.remove(oldPosition);
        this.Animals.put(newPosition,tempAnimal);
        tempAnimal=this.Animals.get(newPosition);
        if(tempAnimal.getEnergy()>=this.maxAnimalEnergy){
            if(this.GrassPoints.get(newPosition)!=null){
                tempAnimal.updateEnergy(1);
                this.GrassPoints.remove(newPosition);
            }
            else{
                tempAnimal.updateEnergy(-1); //at the end of day animal looses energy
            }
        }else{
            tempAnimal.updateEnergy(-1);
        }
    }

    public void animalDies(PlayingCell position){
        this.Animals.remove(position);
    }

    public void grassEaten(Vector2d position){
        this.GrassPoints.remove(position);
    }

    public int getMapSize(){
        return this.mapSize;
    }

    public void updateNewAnimalsPositions(Animal a){
        this.newAnimalPositions.add(a);
    }


}
