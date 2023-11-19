package cz.cvut.fel.omo.smartHome.model.usable;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public interface Usable {

    void useBy(Creature creature);

    void repair();

    void breakUsable();
}
