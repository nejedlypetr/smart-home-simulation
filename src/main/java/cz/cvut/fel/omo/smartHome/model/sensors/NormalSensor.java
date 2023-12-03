package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public class NormalSensor implements SensorInterface {
    private Room room;

    private House house;
    private HeatPump heatPump;

    public NormalSensor(Room room) {
        this.room = room;
    }

    public void measureTemperature() {
        int temp = RandomPicker.getRandomInt(15,31);
        if (temp == 15) notifyHouseCold();
        if (temp == 30) notifyHouseHot();
    }
    public void notifyHouseHot() {
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"hot",heatPump," heats down " + room.getName() + " in " + room.getFloor().getName() +" using Heat Pump. Consumption lowered by 10%");
        house.sensorUpdate(event);
    }
    public void notifyHouseCold() {
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"cold",heatPump," heats up " + room.getName() + " in " + room.getFloor().getName() +" using Heat Pump. Consumption raised by 10%");
        house.sensorUpdate(event);
    }

    public void setHeatPump(HeatPump heatPump) {
        this.heatPump = heatPump;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
