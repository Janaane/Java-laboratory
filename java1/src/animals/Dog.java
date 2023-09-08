package animals;

public class Dog extends Animal{
    private static int count;

    public Dog(String name, int maxSwim, int age) {

        super(name, 500, maxSwim, age);
        count++;
    }
    public static int getCount(){
        return count;
    }

}
