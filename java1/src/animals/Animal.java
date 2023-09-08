package animals;

public abstract class Animal {
    private String name;
    private int maxRun;
    private int maxSwim;
    private int age;
    private static int count;
    //private String habitat;

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

//    public Animal(String name, int maxRun, int maxSwim, int age, String habitat) {
//        this.name = name;
//        this.maxRun = maxRun;
//        this.maxSwim = maxSwim;
//        this.age = age;
//        this.habitat = habitat;
//        count++;
//    }
    public void run(int distance) {
        if (distance < maxRun) {
            System.out.println(name + " успешно пробежал " + distance + " метров");
        } else {
            System.out.println(name + " не пробежал " + distance + " метров");
        }
    }
    public void swim(int distance) {
        if (getClass().getSimpleName() == "animals.Cat"){
            System.out.println(name + " не умеет плавать, потому что является котом");
        }
        else if (distance < maxSwim) {
            System.out.println(name + " успешно проплыл " + distance + " метров");
        } else {
            System.out.println(name + " не проплыл " + distance + " метров");
        }
    }




}
