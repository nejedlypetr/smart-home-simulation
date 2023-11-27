package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.event.Event;

public class HeatPump extends Device {
    public HeatPump() {
        super(1,"Winter is coming... ");
    }

    @Override
    public void useBy(Creature creature) {
        setUsedThisTurn(true);
        System.out.print("\n" + creature + "is in " + getRoom().getFloor() + " in " + getRoom() + ". " + creature.getName() + " is playing with control buttons for Heat Pump. Nothing happens.");
    }

    public void handleEvent(Event event) {

        if (event.getDescription().equals("hot")) {
            setElectricityConsumption((int)(getElectricityConsumption()*1.1));
        } else {
            setElectricityConsumption((int)(getElectricityConsumption()*0.9));
        }
    }
}
