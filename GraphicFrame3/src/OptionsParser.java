public class OptionsParser {

    public OptionsParser(){

    }

    public MoveDirection[] parse(String[] moves_str){
        MoveDirection[] moves = new MoveDirection[moves_str.length];
        int counter=0;
        for(String s : moves_str){
            switch (s){
                case ("f"):
                case ("forward"):
                    moves[counter]=MoveDirection.FORWARD;
                    counter++;
                    break;
                case ("b"):
                case ("backward"):
                    moves[counter]=MoveDirection.BACKWARD;
                    counter++;
                    break;
                case ("r"):
                case ("right"):
                    moves[counter]=MoveDirection.RIGHT;
                    counter++;
                    break;
                case ("l"):
                case ("left"):
                    moves[counter]=MoveDirection.LEFT;
                    counter++;
                    break;
            }
        }
        return  moves;
    }
}

