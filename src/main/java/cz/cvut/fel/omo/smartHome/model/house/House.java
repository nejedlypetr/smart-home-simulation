package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;
import cz.cvut.fel.omo.smartHome.model.weatherStation.WeatherStationFacade;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;
import java.util.Random;

public class House {
    private List<Creature> creatures;
    private List<SportEquipment> sportEquipments;
    private List<Floor> floors;
    private WeatherStationFacade weatherStation;

    public House(List<Creature> creatures, List<SportEquipment> sportEquipments, List<Floor> floors) {
        this.creatures = creatures;
        this.sportEquipments = sportEquipments;
        this.floors = floors;
        weatherStation = new WeatherStationFacade();
    }

    public void simulateNextStep() {
        weatherStation.getWeatherReport();
        for (Creature creature : creatures) {
            Activity activity = getRandomActivityFor(creature);
            creature.doActivity(activity);
        }
    }

    private Activity getRandomActivityFor(Creature creature) {
        Floor floor = RandomListElementPicker.pickRandomElement(floors);
        System.out.print(creature + "is in " + floor.getName() + ". ");
        return floor.getRandomActivityFor(creature);
    }
}
