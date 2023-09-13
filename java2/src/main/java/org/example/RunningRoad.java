package org.example;

public class RunningRoad implements Challenge{

    public RunningRoad(RoadLenght roadLenght) {
        this.roadLength = roadLenght.getLenght();
    }
    int roadLength;
    @Override
    public boolean contest(Participant participant) {
        return participant.run(roadLength);
    }
}
