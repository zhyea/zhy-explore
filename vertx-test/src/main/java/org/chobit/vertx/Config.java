package org.chobit.vertx;

/**
 * @author robin
 */
public final class Config {


    /**
     * Host
     */
    public static final String HOST = "127.0.0.1";

    /**
     * Port
     */
    public static final int PORT = 8190;

    /**
     * End Flag
     */
    public static final String FLAG_END = "EOF";

    /**
     * New line
     */
    public static final String NEW_LINE = "\n";


    private Config() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed!");
    }

}
