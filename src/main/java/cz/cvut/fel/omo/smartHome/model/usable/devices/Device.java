package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;

public abstract class Device implements Usable {
    private int cost;
    private int lifespan = 10;
    private int electricityConsumption = 100;
    private String documentation;
    private DeviceState state = DeviceState.ACTIVE;
    private boolean usedThisTurn = false;

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    @Override
    public void useBy(Creature creature) {
        System.out.print(creature + "is using " + this.getClass().getSimpleName() + ".\n");
    }

    public int update() {
        if (usedThisTurn) {
            return 0;
        } else {
            if (state == DeviceState.ACTIVE) {
                lifespan--;
                if (lifespan<=0) {
                    breakUsable();
                }
                return electricityConsumption;
            } else if (state == DeviceState.IDLE) {
                lifespan--;
                if (lifespan<=0) {
                    breakUsable();
                }
                return 1;
            } else return 0;
        }
    }

    @Override
    public void breakUsable() {
        System.out.println(getClass().getSimpleName() + " broke!!!");
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

    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(int electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }
}
