package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;

import java.util.Random;

public abstract class Device implements Usable {
    private int cost;
    private int lifespan = 1;
    private int electricityConsumption = 100;
    private String documentation = "\"Have you tried turning it OFF and ON?\" ";
    private DeviceState state = DeviceState.IDLE;
    private boolean usedThisTurn = false;
    private static final int IDLE_ELECTRICITY_CONSUMPTION = 1;
    private Room room;

    @Override
    public void useBy(Creature creature) {
        System.out.print("\n" + creature + "is in " + room.getFloor() +". " + creature.getName() + " is in " + room + ". " + creature.getName() + " is using " + this.getClass().getSimpleName() + ".");
        usedThisTurn = true;
    }

    public int update() {
        if (usedThisTurn) {
            usedThisTurn = false;
            updateLifespan(-3);
            if (!isBroken()) {
                setDeviceToNextState();
            }

            return electricityConsumption;
        }

        if (state == DeviceState.ACTIVE) {
            updateLifespan(-3);
            return electricityConsumption;
        }

        if (state == DeviceState.IDLE) {
            updateLifespan(-1);
            return IDLE_ELECTRICITY_CONSUMPTION;
        }

        if (state == DeviceState.BROKEN) {
            System.out.print("\n" + getClass().getSimpleName() + " in " + room + " in " + room.getFloor() + " is still broken and needs to be repaired!");
        }
        return 0;
    }

    private void updateLifespan(int i) {
        lifespan += i;
        if (lifespan <= 0) {
            breakUsable();
        }
    }

    @Override
    public void breakUsable() {
        System.out.print("\n" + getClass().getSimpleName() + " in " + room + " in " + room.getFloor() + " broke this step and needs to be repaired!");
        state = DeviceState.BROKEN;
        DeviceEvent event = new DeviceEvent(room, room.getFloor(), "Event description",this, null);
        room.getFloor().getHouse().addEvent(event);
    }

    public boolean isBroken() {
        return getState()==DeviceState.BROKEN;
    }

    protected void setDeviceToNextState() {
         setState(DeviceState.IDLE);
    }

    public DeviceState getState() {
        return state;
    }

    @Override
    public void repair(Creature creature) {
        setDeviceToNextState();
        Random random = new Random();
        lifespan = 1000;
    }

    public void handle(Creature creature) {
        System.out.println("handling ");
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public void setUsedThisTurn(boolean usedThisTurn) {
        this.usedThisTurn = usedThisTurn;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDocumentation() {
        return documentation;
    }
}
