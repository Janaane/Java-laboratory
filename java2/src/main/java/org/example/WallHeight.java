package org.example;

public enum WallHeight {
    LOW(5),
    MEDIUM(10),
    HIGH(20);
    private int height;
    WallHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }
}
