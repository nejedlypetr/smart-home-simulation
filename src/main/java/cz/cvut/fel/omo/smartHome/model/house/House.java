package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.model.usable.devices.DeviceIterator;
import cz.cvut.fel.omo.smartHome.model.usable.devices.DeviceState;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;
import cz.cvut.fel.omo.smartHome.model.weatherStation.WeatherStationFacade;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;
import java.util.Random;

public class House {
    private final List<Creature> creatures;
    private final List<SportEquipment> sportEquipments;
    private List<Floor> floors;
    private final WeatherStationFacade weatherStation;
    private final DeviceIterator deviceIterator;
    private double roundConsumption = 0;
    private double totalConsumption = 0;

    public House(List<Creature> creatures, List<SportEquipment> sportEquipments, List<Floor> floors) {
        this.creatures = creatures;
        this.sportEquipments = sportEquipments;
        this.floors = floors;
        weatherStation = new WeatherStationFacade();
        deviceIterator = new DeviceIterator(this);
    }

    public void simulateNextStep() {
        roundConsumption = 0;
        weatherStation.getWeatherReport();
        for (Creature creature : creatures) {
            Activity activity = getRandomActivityFor(creature);
            creature.doActivity(activity);
        }

        while (deviceIterator.hasNext()) {
            Device device = deviceIterator.next();
            roundConsumption += device.update();
//            System.out.println(device.getClass().getSimpleName());
        }
        System.out.println(totalConsumption/1000 + " kWh of electricity consumed this round.");
        totalConsumption += roundConsumption;
    }

    private Activity getRandomActivityFor(Creature creature) {
        Floor floor = RandomListElementPicker.pickRandomElement(floors);
        System.out.print(creature + "is in " + floor.getName() + ". ");
        return floor.getRandomActivityFor(creature);
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
