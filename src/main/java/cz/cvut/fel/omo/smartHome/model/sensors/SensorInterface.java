package cz.cvut.fel.omo.smartHome.model.sensors;

public interface SensorInterface {
    public void measureTemperature();
    public void notifyHouseHot();
    public void notifyHouseCold();
}
