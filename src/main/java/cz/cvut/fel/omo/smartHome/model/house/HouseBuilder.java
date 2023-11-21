package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.creature.Adult;
import cz.cvut.fel.omo.smartHome.model.creature.Baby;
import cz.cvut.fel.omo.smartHome.model.creature.Child;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.sport.Bicycle;
import cz.cvut.fel.omo.smartHome.model.usable.sport.Ski;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseBuilder {
    private List<Creature> creatures;
    private List<SportEquipment> sportEquipments;
    private List<Floor> floors;

    public HouseBuilder() {}

    public HouseBuilder withCreatures(List<Creature> creatures) {
        this.creatures = creatures;
        return this;
    }

    public HouseBuilder withCreatures() {
        this.creatures = new ArrayList<>(Arrays.asList(
                new Adult("John"),
                new Adult("Mary"),
                new Child("David"),
                new Child("Charles"),
                new Child("Emily"),
                new Baby("Lily")
        ));
        return this;
    }

    public HouseBuilder withSportEquipments(List<SportEquipment> sportEquipments) {
        this.sportEquipments = sportEquipments;
        return this;
    }

    public HouseBuilder withSportEquipments() {
        this.sportEquipments = new ArrayList<>(Arrays.asList(
            new Ski(),
            new Bicycle()
        ));
        return this;
    }

    public HouseBuilder withFloors(List<Floor> floors) {
        this.floors = floors;
        return this;
    }

    public HouseBuilder withFloors() {
        Floor floor1 = new FloorBuilder()
            .withName("First floor")
            .withRooms()
            .build();

        Floor floor2 = new FloorBuilder()
            .withName("Second floor")
            .withRooms()
            .build();

        this.floors = new ArrayList<>(Arrays.asList(floor1, floor2));
        return this;
    }

    public House build() {
        if (creatures == null || sportEquipments == null || floors == null) {
            throw new IllegalArgumentException("Incomplete set of required arguments!");
        }
        if (creatures.isEmpty() || sportEquipments.isEmpty() || floors.isEmpty()) {
            throw new IllegalArgumentException("Empty required argument!");
        }
        House house = new House(creatures, sportEquipments, floors);
        for (Floor floor : floors) {
            floor.setHouse(house);
        }
        for (SportEquipment sportEquipment : sportEquipments) {
            sportEquipment.setHouse(house);
        }
        return house;
    }

}
