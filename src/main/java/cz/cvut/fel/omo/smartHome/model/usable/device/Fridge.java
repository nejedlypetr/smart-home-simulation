package cz.cvut.fel.omo.smartHome.model.usable.device;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.usable.device.state.ActiveDeviceState;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

public class Fridge extends Device {
    private int foodInside;

    public Fridge() {
        super(40, "\"How to stop global warming? Just open all fridges.\" ");
        this.foodInside = RandomPicker.getRandomInt(2,5);
    }

    @Override
    public void useBy(Creature creature) {
        if (foodInside > 0) {
            foodInside--;
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is taking food from Fridge. " + foodInside + " portions left" + ".");
            if (foodInside == 0) {
                Reporter.getInstance().log(" Fridge is out of food!");
                DeviceEvent event = new DeviceEvent(getRoom(), getRoom().getFloor(), "fridge event", this, " goes to buy food to fill the Fridge in " + getRoom().getName() +" in "+getRoom().getFloor().getName());
                getRoom().getFloor().getHouse().addEvent(event);
            }
            setUsedThisTurn(true);
        } else {
            Reporter.getInstance().log("\n" + creature + " wanted to take food from Fridge in " + getRoom().getName() + " in " + getRoom().getFloor().getName()  + " but there is no food inside.");
        }
    }

    @Override
    protected void setDeviceToNextState() {
        setState(new ActiveDeviceState(this));
    }

    public void handleEvent(Event event) {
        foodInside = RandomPicker.getRandomInt(3,7);
    }

    public void setFoodInside(int foodInside) {
        this.foodInside = foodInside;
    }
}
