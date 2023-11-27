package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public class NormalSensor implements SensorInterface {
    private Room room;
    private HeatPump heatPump;

    public NormalSensor(Room room) {
        this.room = room;
    }

    public void measureTemperature() {
        int temp = RandomPicker.getRandomInt(15,30);
//        System.out.println("measuring: " + temp);
        if (temp > 15) notifyHouseCold();
        if (temp == 29) notifyHouseHot();
    }
    public void notifyHouseHot() {
        System.out.print("\nIt is too hot in " + room + " in " + room.getFloor() + ".");
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"hot",heatPump,"heats up " + room + " in " + room.getFloor() +" using Heat Pump. Consumption raised by 10%.");
        room.getFloor().getHouse().addEvent(event);
        heatPump.setElectricityConsumption((int)(heatPump.getElectricityConsumption()*1.1));
    }
    public void notifyHouseCold() {
        System.out.print("\nIt is too cold in " + room + " in " + room.getFloor() + ".");
        DeviceEvent event = new DeviceEvent(room,room.getFloor(),"cold",heatPump,"heats down " + room + " in " + room.getFloor() +" using Heat Pump. Consumption lowered by 10%.");
        room.getFloor().getHouse().addEvent(event);
    }

    public void setHeatPump(HeatPump heatPump) {
        this.heatPump = heatPump;
    }
}
