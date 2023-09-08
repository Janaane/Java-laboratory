package animals;

public class Moose extends WildAnimals{
    private static int count;

    public Moose(String name, int maxRun, int maxSwim, int age, String habitat){
        super(name, maxRun, maxSwim, age, habitat);
        count++;
    }
    public static int getCount(){
        return count;
    }

}
