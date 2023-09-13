package org.example;

public class RunningRoad implements Challenge{
    private int roadLength;
    public RunningRoad(RoadLenght roadLenght) {
        this.roadLength = roadLenght.getLenght();
    }
    @Override
    public boolean contest(Participant participant) {
        return participant.run(roadLength);
    }
}
