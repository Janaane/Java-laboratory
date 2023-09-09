package animals;
public abstract class WildAnimals extends Animal {
    private String habitat;
    static private int wildCount;
    public WildAnimals(String name, int maxRun, int maxSwim, int age, String habitat){
        super(name, maxRun, maxSwim, age);
        this.habitat = habitat;
        wildCount++;
    }
    public static int getCountWild(){
        return wildCount;
    }
    public String getHabitat(){
        return habitat;
    }
}
