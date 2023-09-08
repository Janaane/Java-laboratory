package animals;

public class Lion extends WildAnimals{
    private static int count;

    public Lion(String name, int maxRun, int maxSwim, int age, String habitat){
        super(name, maxRun, maxSwim, age, habitat);
        count++;
    }
    public static int getCount(){
        return count;
    }

}
