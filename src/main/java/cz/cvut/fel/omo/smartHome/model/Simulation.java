package cz.cvut.fel.omo.smartHome.model;

import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.HouseBuilder;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;

import java.util.List;

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

    public void setupHouse() {
        HeatPump heatPump = new HeatPump();
        Room newRoom = new Room("Basement", List.of(),List.of(heatPump));
        heatPump.setRoom(newRoom);
        house.getFloors().get(0).addRoom(newRoom);
        for (Floor floor : house.getFloors()) {
            for (Room room : floor.getRooms()) {
                room.getSensor().setHeatPump(heatPump);
                room.getSensor().setHouse(house);
            }
        }
    }

    public House getHouse() {
        return house;
    }
}
