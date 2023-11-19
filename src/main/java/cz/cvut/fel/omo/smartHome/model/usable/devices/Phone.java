package cz.cvut.fel.omo.smartHome.model.usable.devices;

import java.util.Random;

public class Phone extends Device {
    @Override
    public void use() {
        System.out.printf(String.valueOf(getLifespan()));
        setUsedThisTurn(true);
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            System.out.println(" is scrolling Instagram on " + getClass().getSimpleName() + ".");
        } else {
            System.out.println(" is playing Subway Surfers on " + getClass().getSimpleName() + ".");
        }
        setLifespan(getLifespan()-1);
        if (getLifespan() < 0) {
            breakUsable();
        }
    }

    @Override
    public void repair() {

    }
}
