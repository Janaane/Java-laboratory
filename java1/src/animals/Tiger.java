package animals;

public class Tiger extends Animal {
    private static int count;
    public Tiger(String name,  int maxRun, int maxSwim, int age) {
        super(name,  maxRun, maxSwim, age);
        count++;
    }
    public static int getCount(){
        return count;
    }
}
