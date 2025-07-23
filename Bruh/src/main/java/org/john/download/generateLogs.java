package org.john.download;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class generateLogs {
    public static void generateLog(String logName, String logPathName, Throwable e) {
        Logger logger = Logger.getLogger(logName);
        try {
            File file = new File(logPathName);
            if (file.createNewFile() || file.exists()) {
                FileHandler fileHandler = new FileHandler(logPathName, true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
                logger.setLevel(Level.INFO);

                if (e != null) {
                    logger.log(Level.INFO, "Exception occurred", e);
                } else {
                    logger.info("Throwable e is null in generateLog");
                }
                fileHandler.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception ex) {
            System.out.println("Unknown exception, cannot generate log");
        }
    }
}
