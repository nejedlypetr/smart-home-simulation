package cz.cvut.fel.omo.smartHome.model.weatherStation;

import java.util.Random;

public class Barometer {
    public int measurePressure() {
        Random random = new Random();
        return random.nextInt(980,1030);
    }
}
