package org.example;

public class Cat implements Participant {
    int maxHeight;
    int maxLenght;
    String name;

    public Cat(String name, int maxHeight, int maxLenght) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLenght = maxLenght;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxLenght) {
            System.out.println("Кот " + this.name + " пробежал " + distance + " метров");
            return true;
        } else {
            System.out.println("Кот " + this.name + " не смог пробежать " + distance + " метров - выбывает");
            return false;
        }
    }
    @Override
    public boolean jump(int height) {
        if (height <= maxHeight) {
            System.out.println("Кот " + this.name + " перепрыгнул " + height + " метров");
            return true;
        } else {
            System.out.println("Кот " + this.name + " не смог перепрыгнуть " + height + " метров - выбывает");
            return false;
        }
    }
}
