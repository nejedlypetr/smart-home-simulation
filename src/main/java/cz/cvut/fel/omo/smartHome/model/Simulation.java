package cz.cvut.fel.omo.smartHome.model;

import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.HouseBuilder;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.sensors.CrazySensorAdapter;
import cz.cvut.fel.omo.smartHome.model.sensors.CrazySenzor;
import cz.cvut.fel.omo.smartHome.model.usable.devices.HeatPump;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

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

    public void setupHeatPumpAndSensors() {
        HeatPump heatPump = new HeatPump();
        Room newRoom = new Room("Basement", List.of(),List.of(heatPump));
        heatPump.setRoom(newRoom);
        house.getFloors().get(0).addRoom(newRoom);

        CrazySenzor crazySenzor = new CrazySenzor(newRoom);
        CrazySensorAdapter adapter = new CrazySensorAdapter(crazySenzor);
        newRoom.setSensor(adapter);

        for (Floor floor : house.getFloors()) {
            for (Room room : floor.getRooms()) {
                room.getSensor().setHeatPump(heatPump);
            }
        }
    }

    public String getHouseConfiguration() {
        return house.toString();
    }

    public void printHouseConsumptionStatistics() {
        house.printTotalConsumptionStatistics();
    }
}
