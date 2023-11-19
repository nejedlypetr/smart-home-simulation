package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoDeviceAvailableException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.creature.Decision;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.model.usable.devices.DeviceIterator;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;
import cz.cvut.fel.omo.smartHome.model.weatherStation.WeatherStationFacade;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.ArrayList;
import java.util.List;

public class House {
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

        for (Creature creature : creatures) {
            Decision decision = creature.makeDecision(events);
            handleDecision(creature, decision);
        }

        // Reset the usage status of all sports equipment for the next turn
        sportEquipments.forEach(sportEquipment -> sportEquipment.setUsedThisTurn(false));


        while (deviceIterator.hasNext()) {
            Device device = deviceIterator.next();
            roundConsumption += device.update();
//            System.out.println(device.getClass().getSimpleName());
        }
        System.out.println("\n" + totalConsumption / 1000 + " kWh of electricity consumed this round.");
        totalConsumption += roundConsumption;
    }

    private void handleDecision(Creature creature, Decision decision) {
        switch (decision) {
            case EVENT -> {
                // todo
                System.out.println(creature + "is handling EVENT.");
            }
            case DEVICE -> {
                try {
                    Device device = getRandomDeviceFor(creature);
                    device.useBy(creature);
                } catch (NoDeviceAvailableException e) {
                    System.out.println(creature + "could not found any available device in this room, all of them are either broken or being used by someone else.\n");
                }
            }
            case SPORT_DEVICE -> {
                List<SportEquipment> availableSportEquipments = sportEquipments
                        .stream()
                        .filter(sportEquipment -> !sportEquipment.isUsedThisTurn())
                        .toList();
                if (availableSportEquipments.isEmpty()) {
                    System.out.println(creature + "could not found any available sport equipment, all of them are either broken or being used by someone else.\n");
                    return;
                }
                SportEquipment sportEquipment = RandomListElementPicker.pickRandomElement(availableSportEquipments);
                sportEquipment.useBy(creature);
            }
            case ACTIVITY -> {
                Activity activity = getRandomActivityFor(creature);
                creature.doActivity(activity);
            }
        }
    }

    private Activity getRandomActivityFor(Creature creature) {
        Floor floor = RandomListElementPicker.pickRandomElement(floors);
        System.out.print(creature + "is in " + floor.getName() + ". ");
        return floor.getRandomActivityFor(creature);
    }

    private Device getRandomDeviceFor(Creature creature) throws NoDeviceAvailableException {
        Floor floor = RandomListElementPicker.pickRandomElement(floors);
        System.out.print(creature + "is in " + floor.getName() + ". ");
        return floor.getRandomDeviceFor(creature);
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
