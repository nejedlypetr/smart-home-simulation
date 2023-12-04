package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoDeviceAvailableException;
import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.creature.Decision;
import cz.cvut.fel.omo.smartHome.model.event.CreatureEvent;
import cz.cvut.fel.omo.smartHome.model.event.DeviceEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.model.usable.devices.DeviceIterator;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;
import cz.cvut.fel.omo.smartHome.model.weatherStation.WeatherStationFacade;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.AdultComparator;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class House implements RandomActivityFinderComposite {
    private final List<Creature> creatures;
    private final List<SportEquipment> sportEquipments;
    private List<Floor> floors;
    private final WeatherStationFacade weatherStation;
    private final DeviceIterator deviceIterator;
    private double roundConsumption = 0;
    private double totalConsumption = 0;
    private List<Event> events;

    public House(List<Creature> creatures, List<SportEquipment> sportEquipments, List<Floor> floors) {
        this.creatures = creatures;
        this.sportEquipments = sportEquipments;
        this.floors = floors;
        weatherStation = new WeatherStationFacade();
        deviceIterator = new DeviceIterator(this);
        events = new ArrayList<>();
    }

    public void simulateNextStep() {
        roundConsumption = 0;
        weatherStation.getWeatherReport();

        Collections.sort(creatures, new AdultComparator()); // shift adults to end of the list

        // Choose something to do for every creature (use device/sport equipment, do activity, handle/generate event)
        for (Creature creature : creatures) {
            Decision decision = creature.makeDecision(events);
            handleDecision(creature, decision);
        }

        // Add events for broken sport equipment and reset them for next turn
        for (SportEquipment se : sportEquipments) {
            se.setUsedThisTurn(false);
            if (se.isBroken()) {
                addEvent(se.generateBrokenEvent());
            }
        }

        Reporter.getInstance().log("\n");
        for (Floor floor : floors) {
            for (Room room : floor.getRooms()) {
                room.getSensor().measureTemperature();
            }
        }

        // Calculate consumption
        Reporter.getInstance().log("\n\nBroken devices:");
        deviceIterator.updateDevices(this);
        while (deviceIterator.hasNext()) {
            Device device = deviceIterator.next();
            roundConsumption += device.update();
        }
        totalConsumption += roundConsumption;

        Reporter.getInstance().log("\n\n" + roundConsumption / 1000 + " kWh of electricity consumed this round.");
        Reporter.getInstance().log("\n" + totalConsumption / 1000 + " kWh of electricity consumed in total.");
    }

    private void handleDecision(Creature creature, Decision decision) {
        Optional<CreatureEvent> ce = events.stream()
                .filter(event -> event instanceof CreatureEvent)
                .map(event -> (CreatureEvent) event)
                .filter(creatureEvent -> creatureEvent.getCreature().equals(creature))
                .findFirst();

        // check for an ongoing creature event
        if (ce.isPresent()) {
            ce.get().print();
            return;
        }

        switch (decision) {
            case HANDLE_EVENT -> {
                Event eventToHandle = events.get(0);
                events.remove(0);
                eventToHandle.handleBy(creature);
            }
            case GENERATE_EVENT -> {
                Floor floor = RandomPicker.pickRandomElementFromList(floors);
                Room room = RandomPicker.pickRandomElementFromList(floor.getRooms());
                addEvent(creature.generateEvent(floor, room));
            }
            case DEVICE -> {
                try {
                    Device device = getRandomDeviceFor(creature);
                    device.useBy(creature);
                } catch (NoDeviceAvailableException e) {
                    Reporter.getInstance().log(e.getMessage());
                }
            }
            case SPORT_DEVICE -> {
                List<SportEquipment> availableSportEquipments = sportEquipments
                        .stream()
                        .filter(sportEquipment -> !sportEquipment.isUsedThisTurn() && sportEquipment.getLifespan() > 0)
                        .toList();
                if (availableSportEquipments.isEmpty()) {
                    Reporter.getInstance().log("\n" + creature + " could not find any available sport equipment, all of them are either broken or being used by someone else.");
                    return;
                }
                SportEquipment sportEquipment = RandomPicker.pickRandomElementFromList(availableSportEquipments);
                sportEquipment.useBy(creature);
            }
            case ACTIVITY -> {
                try {
                    Activity activity = getRandomActivityFor(creature);
                    creature.doActivity(activity);
                } catch (NoValidActivitiesException e) {
                    Reporter.getInstance().log(creature.getName() + " does not know what to do here, so " + creature.getName() + " started daydreaming.");
                }
            }
        }
    }

    @Override
    public Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException {
        Floor floor = RandomPicker.pickRandomElementFromList(floors);
        Reporter.getInstance().log("\n" + creature + " is in " + floor.getName() + ". ");
        return floor.getRandomActivityFor(creature);
    }

    private Device getRandomDeviceFor(Creature creature) throws NoDeviceAvailableException {
        Floor floor = RandomPicker.pickRandomElementFromList(floors);
        Room room = RandomPicker.pickRandomElementFromList(floor.getRooms());
        List<Device> availableDevices = room.getDevices()
                .stream()
                .filter(device -> !device.isBroken() && !device.isUsedThisTurn())
                .toList();
        if (availableDevices.isEmpty()) {
            throw new NoDeviceAvailableException("\n" + creature + " could not found any available device in " + room.getName() + " in " + floor.getName() + ", all of them are either broken or being used by someone else.");
        }
        return RandomPicker.pickRandomElementFromList(availableDevices);
    }

    public void sensorUpdate(DeviceEvent event) {
        if (event.getDescription().equals("hot")) {
            addEvent(event);
            Reporter.getInstance().log("\nIt is too cold in " + event.getRoom().getName() + " in " + event.getRoom().getFloor().getName() + ".");
        }
        if (event.getDescription().equals("cold")) {
            addEvent(event);
            Reporter.getInstance().log("\nIt is too cold in " + event.getRoom().getName() + " in " + event.getRoom().getFloor().getName() + ".");
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!creatures.isEmpty()) {
            stringBuilder.append("Creatures: ");
            for (Creature creature : creatures) {
                stringBuilder.append(String.format("%n\t%s", creature));
            }
        }

        if (!sportEquipments.isEmpty()) {
            stringBuilder.append("\nSport Equipment: ");
            for (SportEquipment sportEquipment : sportEquipments) {
                stringBuilder.append(String.format("%n\t%s", sportEquipment));
            }
        }

        if (!floors.isEmpty()) {
            stringBuilder.append("\nFloors: ");
            for (Floor floor : floors) {
                stringBuilder.append(String.format("%n\t%s", floor));
            }
        }

        return stringBuilder.toString();
    }
}
