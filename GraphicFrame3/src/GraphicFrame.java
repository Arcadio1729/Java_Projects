import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphicFrame extends JPanel implements ActionListener {

    private int inputGrassFieldAmount=20;
    private int inputMapSize=20;
    final int  MAPWIDTHHEIGHT= 400;
    private int timerCounter=0;
    private Timer t;

    private int animalsAmount=0;
    private int grassAmount=0;
    private Simulator S=new Simulator(this.inputGrassFieldAmount,this.inputMapSize,this.MAPWIDTHHEIGHT);
    private JFrame frame=new JFrame("Game of life.");
    JLabel label;

    public static void main(String[] arg){
        GraphicFrame g = new GraphicFrame();

    }

    public void paint(Graphics g){
        //g.fillOval(10,10,20,50);
        //adam.paint(g,this.inputMapSize,this.MAPWIDTHHEIGHT/this.inputMapSize);
        //ewa.paint(g,this.inputMapSize,this.MAPWIDTHHEIGHT/this.inputMapSize);
        this.animalsAmount=0;
        this.grassAmount=0;
        HashMap<Vector2d,PlayingCell> playingCells =this.S.getPlayingCells();
        for(PlayingCell pc : playingCells.values()){
            pc.clearBoard(g,this.inputMapSize,this.MAPWIDTHHEIGHT/this.inputMapSize);
            this.animalsAmount+=pc.getAnimals().size();
            if(pc.getGrass()!=null) this.grassAmount+=1;
        }

        this.S.makeMyDay();

        playingCells = this.S.getPlayingCells();
        this.animalsAmount=0;
        this.grassAmount=0;
        for(PlayingCell pc : playingCells.values()){
            pc.paint(g,this.inputMapSize,this.MAPWIDTHHEIGHT/this.inputMapSize);
            this.animalsAmount+=pc.getAnimals().size();
            if(pc.getGrass()!=null) this.grassAmount+=1;
        }

        this.timerCounter+=1;

    }


    public GraphicFrame(){

        //frame.setSize(this.MAPWIDTHHEIGHT,this.MAPWIDTHHEIGHT);

        frame.setSize(800,800);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //frame.setLayout(new GridLayout(1,2));
        label=new JLabel();
        label.setBorder(BorderFactory.createDashedBorder(Color.RED));
        label.setBounds(500,500,250,25);


        //frame.add(this.label);
        t=new Timer(10,this);
        t.start();

        frame.add(label);
        frame.add(this);

        //frame.add(this.label);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.remove(this.label);

        this.frame.add(this.label);
        this.label.setText("Animals-"+this.animalsAmount+"; Grass-"+this.grassAmount);

        repaint();

    }

}
