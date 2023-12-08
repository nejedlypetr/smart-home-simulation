package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.device.HeatPump;

public class CrazySensorAdapter implements SensorInterface {
    private final CrazySensor crazySensor;

    public CrazySensorAdapter(CrazySensor crazySensor) {
        this.crazySensor = crazySensor;
    }

    @Override
    public Event measureTemperature() {
        return crazySensor.getTeeeeeeemperature();
    }

    @Override
    public void setHeatPump(HeatPump heatPump) {
        crazySensor.setCraaaazyHeatPump(heatPump);
    }

    @Override
    public void setRoom(Room room) {
        crazySensor.setCraaazyRoom(room);
    }
}
