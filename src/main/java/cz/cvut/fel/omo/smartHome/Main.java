package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class Main {
    public static void main(String[] args) {
        Simulation s = new Simulation();
        s.setupHeatPumpAndSensors();

        Reporter.getInstance().log("========== SMART HOME - HOUSE CONFIGURATION ==========\n");
        Reporter.getInstance().log(s.getHouseConfiguration());

        Reporter.getInstance().log("\n\n\n========== SMART HOME - SIMULATION STARTED! ==========");
        for (int i = 0; i < 10; i++) {
            s.simulateNextStep();
        }

        Reporter.getInstance().log("\n\n\n========== SMART HOME - SIMULATION ENDED! ==========");
        s.printHouseConsumptionStatistics();
    }
}