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
    private static final int IDLE_ELECTRICITY_CONSUMPTION = 1;

    @Override
    public void useBy(Creature creature) {
        System.out.print(creature + "is using " + this.getClass().getSimpleName() + ".\n");
        usedThisTurn = true;
        update();
    }

    public int update() {
        if (usedThisTurn) {
            usedThisTurn = false;
            lifespan--;
            if (lifespan <= 0) {
                breakUsable();
            }

            // todo switch to random device state

            return electricityConsumption;
        }

        if (state == DeviceState.ACTIVE) {
            lifespan--;
            if (lifespan <= 0) {
                breakUsable();
            }
            return electricityConsumption;
        }

        if (state == DeviceState.IDLE) {
            if (lifespan <= 0) {
                breakUsable();
            }
            return IDLE_ELECTRICITY_CONSUMPTION;
        }

        return 0;
    }

    @Override
    public void breakUsable() {
        System.out.printf(" " + getClass().getSimpleName() + " broke!!!");
        state = DeviceState.BROKEN;
    }

    public DeviceState getState() {
        return state;
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }
}
