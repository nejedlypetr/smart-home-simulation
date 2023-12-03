package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class SportEquipmentEvent extends Event {
    private final SportEquipment sportEquipment;

    public SportEquipmentEvent(SportEquipment sportEquipment) {
        super(null, null, null, null);
        this.sportEquipment = sportEquipment;
    }

    @Override
    public void handleBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is repairing " + sportEquipment.getClass().getSimpleName() + ". "+ sportEquipment.getClass().getSimpleName() + " fixed.");
        sportEquipment.repair(creature);
    }
}
