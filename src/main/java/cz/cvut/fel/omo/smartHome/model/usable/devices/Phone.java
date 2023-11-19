package cz.cvut.fel.omo.smartHome.model.usable.devices;

import java.util.Random;

public class Phone extends Device {

    public void use() {
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            System.out.print(" is scrolling Instagram on " + getClass().getSimpleName() + ".");
        } else {
            System.out.print(" is playing Subway Surfers on " + getClass().getSimpleName() + ".");
        }
        super.use();
    }

    @Override
    public void repair() {

    }
}
