
public class World {
    public static void main(String[] args){

    }

    public static void run(){
        System.out.println("Zwierzak idzie do przodu!");
    }

    public static Directions[] changeChars(String[] str){
        Directions[] dirs = new Directions[str.length];
        for(int i=0;i<str.length;i++){
            switch (str[i]){
                case "f":
                    dirs[i]= Directions.FORWARD;
                    break;
                case "b":
                    dirs[i]= Directions.BACK;
                    break;
                case "r":
                    dirs[i]= Directions.RIGHT;
                    break;
                case "l":
                    dirs[i]= Directions.LEFT;
                    break;
            }
        }
        return dirs;
    }

    public static void run(String[] strArr){
        if(strArr.length>1){
            for(int i=0;i<strArr.length;i++){
                switch (strArr[i]) {
                    case "f":
                        System.out.println("Zwierzak idzie do przodu");
                        break;
                    case "b":
                        System.out.println("Zwierzak idzie do tyłu");
                        break;
                    case "r":
                        System.out.println("Zwierzak idzie w prawo");
                        break;
                    case "l":
                        System.out.println("Zwierzak idzie w lewo");
                        break;
                }
            }
        }
    }

    public static void run(Directions[] directions){
        if(directions.length>1){
            for(int i=0;i<directions.length;i++){
                switch (directions[i]) {
                    case FORWARD:
                        System.out.println("Zwierzak idzie do przodu");
                        break;
                    case BACK:
                        System.out.println("Zwierzak idzie do tyłu");
                        break;
                    case RIGHT:
                        System.out.println("Zwierzak idzie w prawo");
                        break;
                    case LEFT:
                        System.out.println("Zwierzak idzie w lewo");
                        break;
                }
            }
        }
    }
}
