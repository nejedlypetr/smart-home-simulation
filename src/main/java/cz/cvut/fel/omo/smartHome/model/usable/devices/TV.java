package cz.cvut.fel.omo.smartHome.model.usable.devices;


import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public class TV extends Device {
    @Override
    public void useBy(Creature creature) {
        System.out.print(creature.getName() + " is watching TV.");
        setUsedThisTurn(true);
        update();
    }

    @Override
    public void repair() {

    }
}
