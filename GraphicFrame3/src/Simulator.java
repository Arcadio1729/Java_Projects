import java.util.HashMap;

public class Simulator {
    private GameField gameMap;
    private Animal adam;
    private Animal ewa;
    private int elementSize;
    public Simulator(int inputGrassFieldAmount,int inputMapSize,int inputMapWidthHeight){
        this.elementSize=inputMapWidthHeight/inputMapSize-2;
        this.gameMap=new GameField(inputGrassFieldAmount,inputMapSize,this.elementSize);
        this.adam = new Animal(gameMap,new Vector2d(5,5));
        this.ewa = new Animal(gameMap,new Vector2d(5,5));
        this.gameMap.place(adam);
        this.gameMap.place(ewa);
        this.gameMap.setGrassFields();
    }

    public HashMap<Vector2d,PlayingCell> getPlayingCells(){
        return this.gameMap.getPlayingCells();
    }

    public void makeMyDay(){
        this.gameMap.moveAnimals();
        this.gameMap.updateMap();
    }
}
