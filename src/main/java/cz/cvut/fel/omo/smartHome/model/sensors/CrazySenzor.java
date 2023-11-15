package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.house.Room;

public class CrazySenzor {
    private Room room;

    public CrazySenzor(Room room) {
        this.room = room;
    }

    public void getTemp() {}
    public void tooHotToHandle() {}
    public void freeeezing() {}
}
