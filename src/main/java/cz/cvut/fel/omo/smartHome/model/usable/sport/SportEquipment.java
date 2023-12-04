package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.SportEquipmentEvent;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public abstract class SportEquipment implements Usable {
    private int cost;
    private int lifespan = 100;
    private boolean usedThisTurn = false;
    private House house;

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public void setUsedThisTurn(boolean usedThisTurn) {
        this.usedThisTurn = usedThisTurn;
    }

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is using " + this.getClass().getSimpleName() + ".");
        usedThisTurn = true;
        updateLifespan(-3);
    }

    @Override
    public void breakUsable() {
        Reporter.getInstance().log(" " + getClass().getSimpleName() + " broke and cannot be used until repaired!");
    }

    @Override
    public void repair(Creature creature) {
        lifespan = 1000;
    }

    public boolean isBroken() {
        return lifespan<=0;
    }

    public SportEquipmentEvent generateBrokenEvent() {
        return new SportEquipmentEvent(this);
    }

    public void updateLifespan(int i) {
        lifespan += i;
        if (lifespan <= 0) {
            breakUsable();
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
