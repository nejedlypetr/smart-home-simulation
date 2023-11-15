package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;

public class Main {
    public static void main(String[] args) {
        System.out.println("Smart home simulation started!");

        Simulation s = new Simulation();
        for (int i = 0; i < 5; i++) {
            s.simulateNextStep();
        }
    }
}