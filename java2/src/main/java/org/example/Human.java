package org.example;

public class Human implements Participant {
    private int maxHeight;
    private int maxLenght;
    private String name;
    public static int superRunCount = 2;


    public Human(String name, int maxHeight, int maxLenght) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLenght = maxLenght;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxLenght) {
            System.out.println("Человек " + this.name + " пробежал " + distance + " метров");
            return true;
        } else if (superRunCount != 0) {
            return superRun();
        }
        else {
            System.out.println("Человек " + this.name + " не смог пробежать " + distance + " метров - выбывает");
            return false;
        }
    }
    @Override
    public boolean jump(int height) {
        if (height <= maxHeight) {
            System.out.println("Человек " + this.name + " перепрыгнул " + height + " метров");
            return true;
        } else {
            System.out.println("Человек " + this.name + " не смог перепрыгнуть " + height + " метров - выбывает");
            return false;
        }
    }
    public boolean superRun() {
        System.out.println("Человек " + this.name + " пробежал с superRun`ом");
        superRunCount--;
        return true;
    }
}
