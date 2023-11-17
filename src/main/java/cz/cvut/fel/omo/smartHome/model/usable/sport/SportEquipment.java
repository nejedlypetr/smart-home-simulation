package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.usable.Usable;

public abstract class SportEquipment implements Usable {
    private int cost;
    private int lifespan;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }
}
