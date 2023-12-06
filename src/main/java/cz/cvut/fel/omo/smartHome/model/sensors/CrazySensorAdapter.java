package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.device.HeatPump;

public class CrazySensorAdapter implements SensorInterface {
    private final CrazySenzor crazySenzor;

    public CrazySensorAdapter(CrazySenzor crazySenzor) {
        this.crazySenzor = crazySenzor;
    }

    @Override
    public Event measureTemperature() {
        return crazySenzor.getTeeeeeeemperature();
    }

    @Override
    public void setHeatPump(HeatPump heatPump) {
        crazySenzor.setHeatPump(heatPump);
    }

    @Override
    public void setRoom(Room room) {
        crazySenzor.setRoom(room);
    }
}
