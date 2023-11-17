package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.usable.Usable;

public abstract class Device implements Usable {
    private int cost;
    private int lifespan;
    private int electricityConsumption;
    private String documentation;
    private boolean usedThisTurn;
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
}
