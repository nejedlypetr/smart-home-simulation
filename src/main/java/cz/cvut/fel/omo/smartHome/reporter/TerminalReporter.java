package cz.cvut.fel.omo.smartHome.reporter;

import java.util.logging.ConsoleHandler;

public class TerminalReporter implements ReporterStrategy {
    @Override
    public void configure(Reporter reporter) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());
        reporter.addHandler(consoleHandler);
    }
}
