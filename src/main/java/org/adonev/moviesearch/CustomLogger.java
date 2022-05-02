package org.adonev.moviesearch;

import java.util.logging.Logger;

public class CustomLogger extends Logger {
    protected static Logger LOG = Logger.getLogger("MovieSearch");
    protected CustomLogger(String name, String resourceBundleName) {
        super("MovieSearch","org.adonev.moviesearch");
    }
}
