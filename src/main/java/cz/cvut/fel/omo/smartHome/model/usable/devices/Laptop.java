package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;

import java.util.Random;

public class Laptop extends Device {
    @Override
    public void useBy(Creature creature) {
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            System.out.print(" is working on OMO seminar project on " + getClass().getSimpleName() + ".");
        } else {
            System.out.print(" is playing League of Legends on " + getClass().getSimpleName() + ".");
        }
        x = random.nextInt(2);
        if (x == 1) {
            setState(DeviceState.IDLE);
        } else {
            setState(DeviceState.OFF);
        }
        System.out.print(getState());
        super.useBy(creature);
    }

    @Override
    public void repair(Creature creature) {

    }
}
