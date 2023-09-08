package animals;

public class Cat extends Animal {
    private static int count;
    public Cat(String name, int maxRun, int maxSwim, int age) {
        super(name, maxRun, maxSwim, age);
        count++;
    }
    public static int getCount(){
        return count;
    }
}
