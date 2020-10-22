package com.kryptonitemod.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrypLogger {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void debug(String message) { LOGGER.debug(message); }
    public static void info(String message) {
        LOGGER.info(message);
    }
    public static void debugProperty(String propertyName, Object property) { LOGGER.debug(propertyName + ": " + property); }
}
