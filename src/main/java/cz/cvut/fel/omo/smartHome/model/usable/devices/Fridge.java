package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Fridge extends Device {
    private int foodIn = 100;

    @Override
    public void useBy(Creature creature) {
        if (foodIn > 0) {
            foodIn--;
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is taking food from Fridge. " + foodIn + " portions left" + ".");
            if (foodIn == 0) {
                Reporter.getInstance().log(" Fridge is out of food!");
                DeviceEvent event = new DeviceEvent(getRoom(), getRoom().getFloor(), "fridge event", this, " goes to buy food for ");
                getRoom().getFloor().getHouse().addEvent(event);
            }
            setUsedThisTurn(true);
        } else {
            Reporter.getInstance().log("\n" + creature + " wanted to take food from Fridge in " + getRoom() + " in " + getRoom().getFloor()  + " but there is no food inside.");
        }
    }

    @Override
    protected void setDeviceToNextState() {
        setState(DeviceState.ACTIVE);
    }

    public void handleEvent(Event event) {
        foodIn = 10;
    }


}
