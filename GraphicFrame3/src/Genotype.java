import java.util.*;


public class Genotype {
    private ArrayList<Integer> genes;
    private HashMap<Integer,Integer> genesDensity;

    public Genotype(){
        this.genes= new ArrayList<Integer>();
        for(int i=0;i<32;i++){
            this.genes.add((int)(Math.random() * 8));
        }
        this.genesDensity=new HashMap<Integer, Integer>();
        for(int i=0;i<8;i++){
            this.genesDensity.put(i,0);
        }
        calculateOccurences();
        cleanGenotype();
    }

    public Genotype(ArrayList<Integer> genes){
        this.genes= genes;
        this.genesDensity=new HashMap<Integer, Integer>();
        for(int i=0;i<8;i++){
            this.genesDensity.put(i,0);
        }
        calculateOccurences();
        cleanGenotype();
    }
    public void cleanGenotype(){
        for(int genVal=0;genVal<7;genVal++){
            if(!this.genes.contains(genVal)){
                for(int i=0;i<7;i++){
                    if(Collections.frequency(this.genes,genVal)>1){
                        this.genes.remove((Integer)i);
                        this.genes.add(genVal);
                        break;
                    }
                }
            }
        }
        Collections.sort(this.genes);
    }

    public void calculateOccurences(){
        for(int i=0;i<8;i++){
            this.genesDensity.put(i,Collections.frequency(this.genes,i));
        }
    }

    public int randomDirection(){
        int r = (int) (32 * Math.random());
        int sum = 0;
        int event = -1;
        for (int i = 0; i < 8 && sum <= r; i++) {
            sum+=this.genesDensity.get(i);
            event = i;
        }
        return event;
    }

    public ArrayList<Integer> getGenes(int startIndex, int endIndex){
        ArrayList<Integer> t = new ArrayList<Integer>(this.genes.subList(startIndex, endIndex));

        ArrayList<Integer> g=t;
        return t;
    }

}
