package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        Lion lion = new Lion("Симба", 12, "456", Lion.Color.ORANGE);
        Lion lion2 = new Lion("Муфас", 6, "100", Lion.Color.BLACK);
        Lion lion3 = new Lion("Лёва", 20, "201", Lion.Color.WHITE);
        Requests.createTable(lion);
        Requests.insertIntoTable(lion);
        Requests.insertIntoTable(lion2);
        Requests.insertIntoTable(lion3);
    }
}