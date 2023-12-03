package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class HeatPump extends Device {
    public HeatPump() {
        super(100,"Winter is coming... ",DeviceState.ACTIVE);
    }

    @Override
    public void useBy(Creature creature) {
        setUsedThisTurn(true);
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + " in " + getRoom().getName() + ". " + creature.getName() + " is playing with control buttons for Heat Pump. Nothing happens.");
    }

    public void handleEvent(Event event) {

        if (event.getDescription().equals("hot")) {
            setElectricityConsumption((int)(getElectricityConsumption()*0.9));
        } else {
            setElectricityConsumption((int)(getElectricityConsumption()*1.1));
        }
    }

    @Override
    public void setDeviceToNextState() {
        setState(DeviceState.ACTIVE);
    }
}
