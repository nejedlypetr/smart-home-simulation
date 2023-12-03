package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Main {
    public static void main(String[] args) {
        Simulation s = new Simulation();
        s.setupHouse();

        Reporter.getInstance().log("========== SMART HOME - HOUSE CONFIGURATION ==========\n");
        Reporter.getInstance().log(s.getHouse().toString());

        Reporter.getInstance().log("\n\n\n========== SMART HOME - SIMULATION STARTED! ==========");
        for (int i = 0; i < 20; i++) {
            s.simulateNextStep();
        }
    }
}