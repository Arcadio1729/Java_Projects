import java.awt.*;

public class Grass {

    private Vector2d position;

    private float energy;

    public Grass(Vector2d position){
        this.position = position;
        this.energy = 5;
    }

    public Grass(Vector2d position, float energy){
        this.position = position;
        this.energy = energy;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }

    public float getEnergy(){return this.energy;}

    public void paint(Graphics g,int elementSize){
        g.setColor(Color.GREEN);
        g.fillOval(this.position.x*elementSize,this.position.y*elementSize,elementSize-2,elementSize-2);
    }
}
