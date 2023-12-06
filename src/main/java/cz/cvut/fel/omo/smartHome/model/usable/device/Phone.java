package cz.cvut.fel.omo.smartHome.model.usable.device;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.Random;

public class Phone extends Device {
    @Override
    public void useBy(Creature creature) {
        setUsedThisTurn(true);
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 1) {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is scrolling Instagram on " + getClass().getSimpleName() + ".");
        } else {
            Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() + ". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is playing Subway Surfers on " + getClass().getSimpleName() + ".");
        }
    }
}
