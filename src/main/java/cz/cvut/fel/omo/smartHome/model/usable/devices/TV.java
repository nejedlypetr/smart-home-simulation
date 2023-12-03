package cz.cvut.fel.omo.smartHome.model.usable.devices;


import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class TV extends Device {

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() +". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is watching TV.");
        setUsedThisTurn(true);
    }

    @Override
    public void repair(Creature creature) {

    }
}
