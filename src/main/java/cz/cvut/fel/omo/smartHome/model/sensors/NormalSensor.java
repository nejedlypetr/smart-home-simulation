package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.house.Room;

public class NormalSensor implements SensorInterface {
    private Room room;

    public NormalSensor(Room room) {
        this.room = room;
    }

    public void measureTemperature() {}
    public void notifyHouseHot() {}
    public void notifyHouseCold() {}

}
