public class SimulationEngine implements IEngine{

    private AbstractWorldMap map;
    private MoveDirection[] directions;
    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map){
        this.map=map;
        this.directions=directions;
    }
    @Override
    public void run() {
        this.map.run(this.directions);
        //System.out.print(map.toString());
    }
}
