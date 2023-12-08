package cz.cvut.fel.omo.smartHome.reporter;

import java.io.IOException;
import java.util.logging.FileHandler;

public class FileReporter implements ReporterStrategy {
    @Override
    public void configure(Reporter reporter) {
        try {
            FileHandler fileHandler = new FileHandler("report.txt");
            fileHandler.setFormatter(new CustomFormatter());
            reporter.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
