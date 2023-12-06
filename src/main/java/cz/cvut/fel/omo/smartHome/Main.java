package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.model.Simulation;
import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static final String FILE_NAME = "house.json";

    public static void main(String[] args) {
        Simulation s = new Simulation();

        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            s = new Simulation(House.configureHouseFromJsonFile(fileReader));
        } catch (FileNotFoundException e) {
            Reporter.getInstance().log("No house.json configuration file found. A default house configuration loaded.\n");
        }

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