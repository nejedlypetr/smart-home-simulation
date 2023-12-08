package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Bicycle extends SportEquipment {
    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is riding a Bicycle.");
        setUsedThisTurn(true);
        updateLifespan(-3);
    }
}
