package org.example;

public class Main {
    public static void main(String[] args) {
        Participant[] participants = {
                new Human("Егор", 5, 600),
                new Human("Андрей", 12, 1000),
                new Human("Женя", 10, 3000),
                new Cat("Мурзик", 4, 100),
                new Robot("Boston", 13, 1800),
                new Robot("Dynamics", 5, 2000),
        };

        Challenge[] challenges = {
                new RunningRoad(RoadLenght.LOW),
                new RunningRoad(RoadLenght.HIGH),
                new Wall(WallHeight.LOW),
                new RunningRoad(RoadLenght.LOW),
                new RunningRoad(RoadLenght.HIGH),
                new Wall(WallHeight.HIGH),
                new RunningRoad(RoadLenght.HIGH),
                new Wall(WallHeight.MEDIUM)
        };

        for (Participant p : participants) {
            for (Challenge c : challenges) {
                if (!c.contest(p)){
                    break;
                }
            }
            System.out.println();
        }
    }
}