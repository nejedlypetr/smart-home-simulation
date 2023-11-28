package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public interface RandomActivityFinderComposite {
    Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException;
}
