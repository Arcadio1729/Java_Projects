import java.awt.*;
import java.util.*;

public class PlayingCell extends Vector2d{

    private ArrayList<Animal> animals;
    private ArrayList<Animal> strongestAnimals;
    private Grass grass;

    private AbstractWorldMap mapObsever;
    private float maxEnergy;

    private HashMap<Integer,Vector2d> animalsNewPositions;

    public PlayingCell(int x, int y,AbstractWorldMap map){
        super(x,y);
        this.animals = new ArrayList<Animal>();
        this.maxEnergy=0;
        this.mapObsever=map;
        this.animalsNewPositions=new HashMap<Integer, Vector2d>();
        this.strongestAnimals=new ArrayList<Animal>();
    }

    private void updateEnergy(){
        float maxEnergy=0;
        for(Animal a : this.animals){
            if(a.getEnergy()>maxEnergy) maxEnergy=a.getEnergy();
        }
        this.maxEnergy=maxEnergy;
    }

    private void findStrongestAnimals(){
        this.maxEnergy=0;
        for(Animal a : this.animals){
            if(a.getEnergy()>this.maxEnergy){
                this.strongestAnimals.clear();
                this.strongestAnimals.add(a);
            }else{
                if(a.getEnergy()==this.maxEnergy){
                    this.strongestAnimals.add(a);
                }
            }
        }
    }

    public void eatGrass(){
        this.updateEnergy();
        this.findStrongestAnimals();
        if(grass!=null && this.animals.size()!=0){
            float dividedEnergy=this.grass.getEnergy()/this.strongestAnimals.size();
            for(Animal a : this.strongestAnimals){
                a.updateEnergy(dividedEnergy);
            }
            grass=null;
        }
    }

    public void useEnergyAnimal(int delta){
        for(Animal a : this.animals){
            a.updateEnergy(delta);
        }
    }
    public void animalDies(){
        if(this.animals.size()!=0){
            Iterator itr = this.animals.iterator();
            while (itr.hasNext())
            {
                Animal a = (Animal)itr.next();
                if (a.getEnergy()<=0)
                    itr.remove();   //animal dies
            }
        }
    }

    public void reproduce(){
        if(this.animals.size()>1){
            this.animals.sort(Comparator.comparing(Animal::getEnergy).reversed());
            Animal a1 = this.animals.get(0);
            Animal a2 = this.animals.get(1);

            int idx1=0;
            int idx2=0;

            while(idx1==idx2){
                idx1=(int)(Math.random() * (32 - 0 + 1) + 0);
                idx2=(int)(Math.random() * (32 - idx1 + 1) + idx1);
            }

            ArrayList<Integer> subGenes1=a1.getSubGenotype(0,idx1);
            ArrayList<Integer> subGenes2=a1.getSubGenotype(idx1,idx2);
            ArrayList<Integer> subGenes3=a2.getSubGenotype(idx2,32);

            ArrayList<Integer> genes = new ArrayList<Integer>();

            float newAnimalEnergy=(float)(0.25*a1.getEnergy()+0.25*a2.getEnergy());

            genes.addAll(subGenes1);
            genes.addAll(subGenes2);
            genes.addAll(subGenes3);
            Animal newAnimal=new Animal(this.mapObsever,this,new Vector2d(this.x,this.y),genes,newAnimalEnergy);
            newAnimal.move();
            this.animals.get(0).updateEnergy((float)-0.25*a1.getEnergy());
            this.animals.get(1).updateEnergy((float)-0.25*a2.getEnergy());
            this.mapObsever.place(newAnimal);

        }
    }


    public void animalMoves(){
        Iterator itr = this.animals.iterator();
        while (itr.hasNext()) {
            Animal a = (Animal)itr.next();
            if (a.getPosition().x!=this.x || a.getPosition().y!=this.y)
                itr.remove();   //animal moved
                this.mapObsever.updateNewAnimalsPositions(a);
        }
        this.animalsNewPositions.clear();
    }

    public void grassEaten(Vector2d position){
        this.mapObsever.grassEaten(position);
    }

    public void addAnimal(Animal a){
        this.animals.add(a);
    }

    public void addGrass(Grass g){
        this.grass=g;
    }

    public Grass getGrass(){
        return this.grass;
    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }



    public void moveAnimals(){
        for(int i=0;i<this.animals.size();i++){
            Vector2d tempVector=this.animals.get(i).move();
        }
        this.animalMoves();
    }

    public void paint(Graphics g,int mapSize,int elementSize){
        if(this.grass!=null) this.grass.paint(g,elementSize);

        for(Animal a: this.animals){
            a.paint(g,mapSize,elementSize,Color.YELLOW);
        }
    }

    public void clearBoard(Graphics g,int mapSize,int elementSize){

        for(Animal a: this.animals){
            a.paint(g,mapSize,elementSize,Color.WHITE);
            g.drawString("",this.x*elementSize,this.y*elementSize);
        }

        if(this.grass!=null) this.grass.paint(g,elementSize);
    }
    public void removeAnimals(){
        int removeIndex=0;
        for(Integer idx : this.animalsNewPositions.keySet()){
            if(idx<=this.mapObsever.getMapSize()){
                this.animals.remove(removeIndex);
                removeIndex=idx-1;
            }
        }
    }

    public boolean verifyPosition(Vector2d position){
        if(position.x<this.mapObsever.getMapSize() && position.y<this.mapObsever.getMapSize() && position.x>=0 && position.y>=0) return true;
        return false;
    }

    public AbstractWorldMap getMapObsever() {
        return this.mapObsever;
    }

    @Override
    public String toString(){
        if(this.grass!=null){
            return "*";
        }else{
            if(this.animals.size()!=0) return "X";
        }
        return "";
    }
}
