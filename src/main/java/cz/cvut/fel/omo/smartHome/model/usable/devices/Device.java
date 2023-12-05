package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.*;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.Usable;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.BrokenDeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.DeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.devices.states.IdleDeviceState;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public abstract class Device implements Usable {
    private int cost;
    private int lifespan = RandomPicker.getRandomInt(10, 50); // 20 - 80
    private int electricityConsumption = 100;
    private String documentation = "\"Have you tried turning it OFF and ON?\" ";
    private DeviceState state = new IdleDeviceState(this);
    private boolean usedThisTurn = false;
    private static final int IDLE_ELECTRICITY_CONSUMPTION = 1;
    private Room room;

    public Device() {}
    public Device(int electricityConsumption, String documentation, int lifespan) {
        this.documentation = documentation;
        this.electricityConsumption = electricityConsumption;
        this.lifespan = lifespan;
    }

    public Device(int electricityConsumption, String documentation) {
        this.electricityConsumption = electricityConsumption;
        this.documentation = documentation;
    }

    public static Device fromString(String type) {
        switch (type) {
            case "Car": return new Car();
            case "Dish washer": return new Dishwasher();
            case "Fridge": return new Fridge();
            case "Laptop": return new Laptop();
            case "Light bulb": return new LightBulb();
            case "Phone": return new Phone();
            case "TV": return new TV();
            case "Washing machine": return new WashingMachine();
            default: throw new RuntimeException("Invalid device type.");
        }
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
        lifespan = RandomPicker.getRandomInt(10,100);
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
        return getClass().getSimpleName() + " with power of " + electricityConsumption + " W and documentation: " + documentation;
    }
}
