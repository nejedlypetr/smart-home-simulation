package cz.cvut.fel.omo.smartHome.reporter;

import java.io.IOException;
import java.util.logging.*;

public class Reporter {
    private static Reporter instance;
    private Logger logger;

    private Reporter() {
        logger = Logger.getLogger("");
        configure();
    }

    public static Reporter getInstance() {
        if (instance == null) {
            synchronized (Reporter.class) {
                if (instance == null) {
                    instance = new Reporter();
                }
            }
        }
        return instance;
    }

    public void log(String msg) {
        logger.info(msg);
    }

    private void configure() {
        // clear default handlers
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        // console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());
        logger.addHandler(consoleHandler);

        // file handler
        try {
            FileHandler fileHandler = new FileHandler("report.txt");
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getMessage();
        }
    }

}
