package cz.cvut.fel.omo.smartHome.model.weatherStation;

import java.util.Random;

public class Thermometer {
    public int measureTemperature() {
        Random random = new Random();
        return random.nextInt(-10,30);
    }
}
