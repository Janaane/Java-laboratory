package animals;
public abstract class Animal {
    private String name;
    private int maxRun;
    private int maxSwim;
    private int age;
    private static int count;
    public String getName(){
        return name;
    }
    public static int getCount() {
        return count;
    }
    public Animal(String name, int maxRun, int maxSwim, int age) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        this.age = age;
        count++;
    }
    public void run(int distance) {
        if (distance < maxRun) {
            System.out.println(name + " успешно пробежал " + distance + " метров");
        } else {
            System.out.println(name + " не пробежал " + distance + " метров");
        }
    }
    public void swim(int distance) {
        if (getClass().getSimpleName().equals("Cat")){
            System.out.println(name + " не умеет плавать, потому что является котом");
        }
        else if (distance < maxSwim) {
            System.out.println(name + " успешно проплыл " + distance + " метров");
        } else {
            System.out.println(name + " не проплыл " + distance + " метров");
        }
    }
}
