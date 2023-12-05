package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;

public interface SensorInterface {
    Event measureTemperature();
    void setHeatPump(HeatPump heatPump);
    void setRoom(Room room);
    static SensorInterface fromString(String type) {
        switch (type) {
            case "Normal sensor": return new NormalSensor(null);
            case "Crazy sensor": return new CrazySensorAdapter(new CrazySenzor(null));
            default: throw new RuntimeException("Invalid sensor type.");
        }
    }
}
