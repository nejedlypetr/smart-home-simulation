package cz.cvut.fel.omo.smartHome.reporter;

import java.util.logging.Logger;

public class Reporter {
    private static Reporter instance;
    private Logger logger;

    private Reporter() {
        logger = Logger.getLogger(Reporter.class.getName());
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

}
