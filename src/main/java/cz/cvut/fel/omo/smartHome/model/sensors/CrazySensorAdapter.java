package cz.cvut.fel.omo.smartHome.model.sensors;

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
}
