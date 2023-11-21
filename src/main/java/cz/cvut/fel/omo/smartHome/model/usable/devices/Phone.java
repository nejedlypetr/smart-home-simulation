package cz.cvut.fel.omo.smartHome.model.usable.devices;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;

import java.util.Random;

public class Phone extends Device {
    @Override
    public void useBy(Creature creature) {
        setUsedThisTurn(true);
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            System.out.print("\n" + creature + "is in " + getRoom().getFloor() + ". " + creature.getName() + " is in " + getRoom() + ". " + creature.getName() + " is scrolling Instagram on " + getClass().getSimpleName() + ".");
        } else {
            System.out.print("\n" + creature + "is in " + getRoom().getFloor() + ". " + creature.getName() + " is in " + getRoom() + ". " + creature.getName() + " is playing Subway Surfers on " + getClass().getSimpleName() + ".");
        }
    }
}
