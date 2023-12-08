package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Ski extends SportEquipment {
    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is skiing.");
        setUsedThisTurn(true);
        updateLifespan(-3);
    }
}
