package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        Lion lion = new Lion("Симба", 12, "456", Lion.Color.ORANGE);
        Lion lion2 = new Lion("Муфас", 6, "100", Lion.Color.BLACK);
        Lion lion3 = new Lion("Лёва", 20, "200", Lion.Color.WHITE);
        Annotation.createTable(lion);
        Annotation.insertIntoTable(lion);
        Annotation.insertIntoTable(lion2);
        Annotation.insertIntoTable(lion3);
    }
}