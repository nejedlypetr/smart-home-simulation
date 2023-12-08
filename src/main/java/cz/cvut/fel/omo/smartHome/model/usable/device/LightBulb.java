package cz.cvut.fel.omo.smartHome.model.usable.device;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.device.state.ActiveDeviceState;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class LightBulb extends Device {

    public LightBulb() {
        super(40, "\"Just buy a new one lol.\" ");
        setState(new ActiveDeviceState(this));
    }

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() +". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is turning the Light bulb on and off again and again.. and again.");
        setUsedThisTurn(true);
    }

    @Override
    protected void setDeviceToNextState() {
        setState(new ActiveDeviceState(this));
    }
}
