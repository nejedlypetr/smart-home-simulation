package cz.cvut.fel.omo.smartHome.reporter;

import java.util.logging.Handler;
import java.util.logging.Logger;

public class Reporter {
    private static Reporter instance;
    private final Logger logger;
    private ReporterStrategy reporterStrategy;

    private Reporter() {
        this.logger = Logger.getLogger("");
        setReporterStrategy(new TerminalReporter());
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

    protected void addHandler(Handler handler) {
        logger.addHandler(handler);
    }

    public void setReporterStrategy(ReporterStrategy reporterStrategy) {
        // clear default handlers
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        this.reporterStrategy = reporterStrategy;
        this.reporterStrategy.configure(this);
    }
}
