package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Adult;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Car extends Device {

    public Car() {
        super(5000, "\nIn case of malfunction return to Elon Musk.\n ");
    }

    @Override
    public void useBy(Creature creature) {
        if (creature.getClass().equals(Adult.class)) {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " goes for a ride with Tesla Cybertruck.");
            setUsedThisTurn(true);
        } else {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " throws baseballs at Tesla Cybertruck.");
        }

    }
}
