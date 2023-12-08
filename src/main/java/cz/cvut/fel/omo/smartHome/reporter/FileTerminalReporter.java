package cz.cvut.fel.omo.smartHome.reporter;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class FileTerminalReporter implements ReporterStrategy {
    @Override
    public void configure(Reporter reporter) {
        // Console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());
        reporter.addHandler(consoleHandler);

        // File
        try {
            FileHandler fileHandler = new FileHandler("report.txt");
            fileHandler.setFormatter(new CustomFormatter());
            reporter.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
