package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.house.Room;

public class SensorB {
    private Room room;

    public SensorB(Room room) {
        this.room = room;
    }

    public void getTemp() {}
    public void tooHotToHandle() {}
    public void freeeezing() {}
}
