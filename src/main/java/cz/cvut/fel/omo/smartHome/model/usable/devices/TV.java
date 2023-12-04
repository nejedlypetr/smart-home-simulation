package cz.cvut.fel.omo.smartHome.model.usable.devices;


import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

import java.util.List;

public class TV extends Device {

    private final List<String> whatToWatch =  List.of("Peaky Blinders", "Shrek","Forrest Gump", "The Godfather");

    public TV() {
        super(102, "\"You thought this was gonna help you to repair the TV? Foolish.\" ");
    }

    @Override
    public void useBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getFloor().getName() +". " + creature.getName() + " is in " + getRoom().getName() + ". " + creature.getName() + " is watching " + RandomPicker.pickRandomElementFromList(whatToWatch) + " on TV.");
        setUsedThisTurn(true);
    }
}
