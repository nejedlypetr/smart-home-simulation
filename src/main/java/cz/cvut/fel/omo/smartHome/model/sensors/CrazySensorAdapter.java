package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;

public class CrazySensorAdapter implements SensorInterface {

    private final CrazySenzor crazySenzor;

    public CrazySensorAdapter(CrazySenzor crazySenzor) {
        this.crazySenzor = crazySenzor;
    }

    @Override
    public void measureTemperature() {
        crazySenzor.getTemp();
    }

    @Override
    public void notifyHouseHot() {
        crazySenzor.tooHotToHandle();
    }

    @Override
    public void notifyHouseCold() {
        crazySenzor.freeeezing();
    }

    @Override
    public void setHeatPump(HeatPump heatPump) {

    }

    @Override
    public void setHouse(House house) {

    }
}
