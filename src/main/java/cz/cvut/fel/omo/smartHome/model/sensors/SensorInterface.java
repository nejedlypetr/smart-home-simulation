package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;

public interface SensorInterface {
    void measureTemperature();
    void notifyHouseHot();
    void notifyHouseCold();

    void setHeatPump(HeatPump heatPump);
}
