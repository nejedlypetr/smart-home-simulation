package cz.cvut.fel.omo.smartHome.model;

import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.HouseBuilder;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

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
                        .withPricePerKWh(5.0)
                        .withCreatures()
                        .withFloors()
                        .withSportEquipments()
                        .build();
        this.hour = 0;
        this.day = 1;
    }

    public void simulateNextStep() {
        Reporter.getInstance().log("\n\n===== Day " + day + ", time: " + hour + ":00 =====\n");
        house.simulateNextStep();
        elapseHour();
    }

    private void elapseHour() {
        hour++;
        if (hour == 24) {
            hour = 0;
            day++;
        }
    }

    public String getHouseConfiguration() {
        return house.toString();
    }

    public void printHouseConsumptionStatistics() {
        house.printTotalConsumptionStatistics();
    }
}
