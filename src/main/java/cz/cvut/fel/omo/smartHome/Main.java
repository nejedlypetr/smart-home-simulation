package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Fridge;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Laptop;

public class Main {
    public static void main(String[] args) {
        System.out.println("Smart home simulation started!");

        Simulation s = new Simulation();
        Laptop laptop = new Laptop();
        s.getHouse().getFloors().get(1).getRooms().get(1).getDevices().add(laptop);

        for (int i = 0; i < 5; i++) {
            s.simulateNextStep();
        }
    }
}