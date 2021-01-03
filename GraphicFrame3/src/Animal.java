import java.awt.*;
import java.util.*;

public class Animal {
    private Vector2d position;
    public Vector2d orientation;
    private int LimitX;
    private int LimitY;
    private Genotype genotype;
    //private ArrayList<IPositionChangeObserver> observers=new ArrayList<IPositionChangeObserver>();

    private PlayingCell observer;
    private AbstractWorldMap map;
    private float energy;

    public Animal(AbstractWorldMap map, Vector2d initialPosition){
        //this.addObserver(map);
        this.map=map;
        //this.observer=observer;
        this.position=initialPosition;
        this.orientation = new Vector2d(0,0);
        this.energy=10;
        this.genotype=new Genotype();
    }

    public Animal(AbstractWorldMap map, PlayingCell observer, Vector2d initialPosition){
        //this.addObserver(map);
        this.map=map;
        this.observer=observer;
        this.position=initialPosition;
        this.orientation = new Vector2d(0,0);
        this.energy=20;
        this.genotype=new Genotype();
    }

    public Animal(AbstractWorldMap map, PlayingCell observer, Vector2d initialPosition,ArrayList<Integer> genes,float energy){
        //this.addObserver(map);
        this.map=map;
        this.observer=observer;
        this.position=initialPosition;
        this.orientation = new Vector2d(0,0);
        this.energy=energy;
        this.genotype=new Genotype(genes);
    }
    public String toString(){
        return this.position.toString()+" - "+this.orientation.toString();
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public Vector2d move() {
        boolean verifiedPosition=false;

        while(verifiedPosition==false){
        int rotateAngle = this.genotype.randomDirection();
        rotate(rotateAngle);
        Vector2d newPotentialPosition = this.position.add(this.orientation);
            verifiedPosition=this.observer.verifyPosition(newPotentialPosition);
            if(verifiedPosition){
                this.position=this.position.add(this.orientation);
            }
        }
        this.observer.animalMoves();
        return this.position;
    }

    public ArrayList<Integer> getSubGenotype(int startIndex,int endIndex){
        return this.genotype.getGenes(startIndex,endIndex);
    }

    public void rotate(int rotateAngle){
        Vector2d Vector=new Vector2d(0,0);
        switch (rotateAngle) {
            case 0:
                this.orientation = new Vector2d(0, 1);
                break;
            case 1:
                this.orientation = new Vector2d(1, 1);
                break;
            case 2:
                this.orientation = new Vector2d(1, 0);
                break;
            case 3:
                this.orientation = new Vector2d(1, -1);
                break;
            case 4:
                this.orientation = new Vector2d(0, -1);
                break;
            case 5:
                this.orientation = new Vector2d(-1, -1);
                break;
            case 6:
                this.orientation = new Vector2d(-1, 0);
                break;
            case 7:
                this.orientation = new Vector2d(-1, 1);
                break;
        }
    }

    public void eatGrass(){
        if(this.energy>=map.maxAnimalEnergy){
            if(map.GrassPoints.get(this.position)!=null){

            }
        }
    }

    public AbstractWorldMap getMap(){
        return this.map;
    }

    public float getEnergy(){
        return this.energy;
    }

    public void updateEnergy(float deltaEnergy){
        this.energy+=deltaEnergy;
    }

    public void setObserver(PlayingCell o){
        this.observer=o;
    }

    public void paint(Graphics g,int mapSize,int animalSize,Color c){
        g.setColor(c);
        g.fillRect(this.position.x*animalSize,this.position.y*animalSize,animalSize-2,animalSize-2);
        g.setColor(Color.BLACK);

        //g.drawString(String.valueOf(this.energy),this.position.x*animalSize-2,this.position.y*animalSize-2);
        g.drawString(String.valueOf(this.energy),this.position.x*animalSize-2,this.position.y*animalSize-2);

    }
}

