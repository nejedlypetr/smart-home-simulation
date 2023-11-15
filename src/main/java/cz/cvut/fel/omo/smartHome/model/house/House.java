package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.SportEquipment;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;

public class House {
    private List<Creature> creatures;
    private List<SportEquipment> sportEquipments;
    private List<Floor> floors;

    public House(List<Creature> creatures, List<SportEquipment> sportEquipments, List<Floor> floors) {
        this.creatures = creatures;
        this.sportEquipments = sportEquipments;
        this.floors = floors;
    }

    public void simulateNextStep() {
        for (Creature creature : creatures) {
            Activity activity = getRandomActivityFor(creature);
            creature.doActivity(activity);
        }
    }

    private Activity getRandomActivityFor(Creature creature) {
        Floor floor = RandomListElementPicker.pickRandomElement(floors);
        System.out.print(creature + "is in " + floor.getName() + ".");
        return floor.getRandomActivityFor(creature);
    }
}
