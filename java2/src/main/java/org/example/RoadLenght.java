package org.example;

public enum RoadLenght {
    LOW(500),
    MEDIUM(1000),
    HIGH(10000);
    private int lenght;
    RoadLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getLenght() {
        return lenght;
    }
}