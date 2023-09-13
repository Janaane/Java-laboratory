package org.example;

public class Wall implements Challenge{
    private int wallHeight;
    public Wall(WallHeight wallHeight) {
        this.wallHeight = wallHeight.getHeight();
    }
    @Override
    public boolean contest(Participant participant) {
        return participant.jump(wallHeight);
    }
}
