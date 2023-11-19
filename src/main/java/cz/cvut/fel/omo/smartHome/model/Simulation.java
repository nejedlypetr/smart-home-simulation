package cz.cvut.fel.omo.smartHome.model;

import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.HouseBuilder;

public class Simulation {
    private House house;
    private int hour;
    private int day;

    public Simulation(House house) {
        this.house = house;
        this.hour = 0;
        this.day = 1;
    }

    public Simulation() {
        this.house = new HouseBuilder()
                        .withCreatures()
                        .withFloors()
                        .withSportEquipments()
                        .build();
        this.hour = 0;
        this.day = 1;
    }

    public void simulateNextStep() {
        printCurrentTimeInfo();
        house.simulateNextStep();
        elapseHour();
    }

    public TimeOfDay getTimeOfDay() {
        return (hour < 7 || hour > 17) ? TimeOfDay.nightTime : TimeOfDay.dayTime;
    }

    private void elapseHour() {
        hour++;
        if (hour == 24) {
            hour = 0;
            day++;
        }
    }

    private void printCurrentTimeInfo() {
        System.out.println();
        System.out.println("======= Day " + day + ", time: " + hour + ":00 =======");
    }

    public House getHouse() {
        return house;
    }
}
