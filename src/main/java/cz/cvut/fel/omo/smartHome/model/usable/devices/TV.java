package cz.cvut.fel.omo.smartHome.model.usable.devices;


import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public class TV extends Device {

    @Override
    public void useBy(Creature creature) {
        System.out.print("\n" + creature + "is in " + getRoom().getFloor() +". " + creature.getName() + " is in " + getRoom() + ". " + creature.getName() + " is watching TV.");
        setUsedThisTurn(true);
    }

    @Override
    public void repair(Creature creature) {

    }
}
