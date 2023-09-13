package org.example;

public class Wall implements Challenge{
    int height;
    public Wall(WallHeight wallHeight) {
        this.height = wallHeight.getHeight();

    }
    @Override
    public boolean contest(Participant participant) {
        return participant.jump(height);
    }
}
