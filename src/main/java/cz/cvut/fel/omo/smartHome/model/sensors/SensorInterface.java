package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;

public interface SensorInterface {
    Event measureTemperature();
    void setHeatPump(HeatPump heatPump);
}
