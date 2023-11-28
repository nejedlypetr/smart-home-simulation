package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public class CrazySenzor {
    private Room room;

    private House house;
    private HeatPump heatPump;

    public CrazySenzor(Room room) {
        this.room = room;
    }

    public void getTemp() {
        int temp = RandomPicker.getRandomInt(15,31);
        if (temp == 15) freeeezing();
        if (temp == 30) tooHotToHandle();
    }
    public void tooHotToHandle() {
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"hot",heatPump,"heats down " + room + " in " + room.getFloor() +" using Heat Pump. Consumption lowered by 10%");
        house.sensorUpdate(event);
    }
    public void freeeezing() {
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"cold",heatPump,"heats up " + room + " in " + room.getFloor() +" using Heat Pump. Consumption raised by 10%");
        house.sensorUpdate(event);
    }

    public void setHeatPump(HeatPump heatPump) {
        this.heatPump = heatPump;
    }

    public void whereIsMyHouse(House house) {
        this.house = house;
    }
}
