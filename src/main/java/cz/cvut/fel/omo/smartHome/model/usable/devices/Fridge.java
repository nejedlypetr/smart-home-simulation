package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;

public class Fridge extends Device {
    private int foodIn = 20;

    @Override
    public void useBy(Creature creature) {
        foodIn--;
        System.out.print("\n" + creature + "is in " + getRoom().getFloor() +". " + creature.getName() + " is in " + getRoom() + ". " + creature.getName() + " is taking food from Fridge. " + foodIn + " portions left" + ".");
        if (foodIn == 0) {
            System.out.print(" Fridge is out of food!");
            DeviceEvent event = new DeviceEvent(getRoom(),getRoom().getFloor(),"fridge event",this);
            getRoom().getFloor().getHouse().addEvent(event);
        }
        setUsedThisTurn(true);
    }

    @Override
    protected void setDeviceToNextState() {
        setState(DeviceState.ACTIVE);
    }

//    @Override
//    public void repair(Creature creature) {
//
//    }

//    @Override
//    public void handle(Creature creature) {
//
//    }


}
