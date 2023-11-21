package cz.cvut.fel.omo.smartHome.model.usable.sport;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.SportEquipmentEvent;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;

public abstract class SportEquipment implements Usable {
    private int cost;
    private int lifespan = 1;
    private boolean usedThisTurn = false;

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public void setUsedThisTurn(boolean usedThisTurn) {
        this.usedThisTurn = usedThisTurn;
    }

    @Override
    public void useBy(Creature creature) {
        System.out.print("\n" + creature + "is using " + this.getClass().getSimpleName() + ".");
        updateLifespan(-3);
    }

    @Override
    public void breakUsable() {
        System.out.print(" " + getClass().getSimpleName() + " broke and cannot be used until repaired!");
        SportEquipmentEvent event = new SportEquipmentEvent(this);
    }

    private void updateLifespan(int i) {
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

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }
}
