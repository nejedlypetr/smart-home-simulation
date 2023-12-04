package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.BrokenDeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.DeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.IdleDeviceState;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.Random;

public abstract class Device implements Usable {
    private int cost;
    private int lifespan = 3;
    private int electricityConsumption = 100;
    private String documentation = "\"Have you tried turning it OFF and ON?\" ";
    private DeviceState state = new IdleDeviceState(this);
    private boolean usedThisTurn = false;
    private static final int IDLE_ELECTRICITY_CONSUMPTION = 1;
    private Room room;

    public Device() {}
    public Device(int lifespan, String documentation) {
        this.lifespan = lifespan;
        this.documentation = documentation;
    }

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is in " + room.getFloor().getName() +". " + creature.getName() + " is in " + room.getName() + ". " + creature.getName() + " is using " + this.getClass().getSimpleName() + ".");
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
        return state.updateDevice();
    }

    public void updateLifespan(int i) {
        lifespan += i;
        if (lifespan <= 0) {
            breakUsable();
        }
    }

    @Override
    public void breakUsable() {
        Reporter.getInstance().log("\n" + getClass().getSimpleName() + " in " + room.getName() + " in " + room.getFloor().getName() + " broke this step and needs to be repaired!");
        state = new BrokenDeviceState(this);
        DeviceEvent event = new DeviceEvent(room, room.getFloor(), "Event description",this, null);
        room.getFloor().getHouse().addEvent(event);
    }

    public boolean isBroken() {
        return getState().getClass().equals(BrokenDeviceState.class);
    }

    protected void setDeviceToNextState() {
         setState(new IdleDeviceState(this));
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

    public void handleEvent(Event event) {}

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

    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(int electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {cost=" + cost +
                ", lifespan=" + lifespan +
                ", electricityConsumption=" + electricityConsumption +
                ", documentation='" + documentation + '\'' +
                ", state=" + state +
                '}';
    }
}
