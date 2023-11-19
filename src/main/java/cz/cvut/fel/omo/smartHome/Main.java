package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Fridge;

public class Main {
    public static void main(String[] args) {
        System.out.println("Smart home simulation started!");

        Simulation s = new Simulation();
        Fridge fridge = new Fridge();
        fridge.setLifespan(1);
        s.getHouse().getFloors().get(1).getRooms().get(1).getDevices().add(fridge);
        for (int i = 0; i < 5; i++) {
            s.simulateNextStep();
        }
    }
}