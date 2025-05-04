package jtm.extra05;

import org.apache.log4j.Logger;

public class Log {

    // Logger for this class
    private static final Logger logger = Logger.getLogger(Log.class);

    // --- Static Logging Methods ---
    public static void trace(String message) {
//        Trace level is not expected by Log.
    }

    public static void debug(String message) {
//        Debug level is not expected by Log.
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    // --- Accessor Method ---
    public static Logger getLoger() {
        return logger;
    }

    // --- Nested Class: ExtLog ---
    public static class ExtLog extends Log {
        private static final Logger extLogger = Logger.getLogger(ExtLog.class);

        public static void trace(String message) {
            extLogger.trace(message);
        }

        public static void debug(String message) {
            extLogger.debug(message);
        }

        public static void info(String message) {
            extLogger.info(message);
        }

        public static void warn(String message) {
            extLogger.warn(message);
        }

        public static void error(String message) {
            extLogger.error(message);
        }

        public static void fatal(String message) {
            extLogger.fatal(message);
        }

        public static Logger getLogger() {
            return extLogger;
        }
    }
}
