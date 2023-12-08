package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.SportEquipmentEvent;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public abstract class SportEquipment implements Usable {
    private int lifespan = RandomPicker.getRandomInt(10,20);
    private boolean usedThisTurn = false;
    private House house;

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is using " + this.getClass().getSimpleName() + ".");
        usedThisTurn = true;
        updateLifespan(-3);
    }

    @Override
    public void breakUsable() {
        Reporter.getInstance().log(" " + getClass().getSimpleName() + " broke and cannot be used until repaired!");
        house.addEvent(new SportEquipmentEvent(this));
    }

    @Override
    public void repair(Creature creature) {
        lifespan = RandomPicker.getRandomInt(20,50);
    }

    @Override
    public boolean isBroken() {
        return lifespan<=0;
    }

    @Override
    public void updateLifespan(int i) {
        lifespan += i;
        if (lifespan <= 0) {
            breakUsable();
        }
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public int getLifespan() {
        return lifespan;
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public void setUsedThisTurn(boolean usedThisTurn) {
        this.usedThisTurn = usedThisTurn;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static SportEquipment fromString(String type) {
        switch (type) {
            case "Bicycle": return new Bicycle();
            case "Ski": return new Ski();
            default: throw new RuntimeException("Invalid sport equipment type.");
        }
    }
}
