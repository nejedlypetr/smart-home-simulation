package cz.cvut.fel.omo.smartHome.utils;

import cz.cvut.fel.omo.smartHome.model.creature.Adult;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;

import java.util.Comparator;

public class AdultComparator implements Comparator<Creature> {
    @Override
    public int compare(Creature creature1, Creature creature2) {
        boolean isCreature1Adult = creature1 instanceof Adult;
        boolean isCreature2Adult = creature2 instanceof Adult;

        if (isCreature1Adult && !isCreature2Adult) {
            return 1; // Put Adult creature after non-Adult creature
        } else if (!isCreature1Adult && isCreature2Adult) {
            return -1; // Put Adult creature before non-Adult creature
        } else {
            return 0; // Maintain the relative order if both are Adults or non-Adults
        }
    }
}
