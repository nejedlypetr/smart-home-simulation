package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.IdleDeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.OffDeviceState;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.Random;

public class Laptop extends Device {
    @Override
    public void useBy(Creature creature) {
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() +". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is working on OMO seminar project on " + getClass().getSimpleName() + ".");
        } else {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() +". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is playing League of Legends on " + getClass().getSimpleName() + ".");
        }
    }

    @Override
    protected void setDeviceToNextState() {
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            setState(new IdleDeviceState(this));
        } else {
            setState(new OffDeviceState(this));
        }
    }
}
