package com.traulko.project.util;

/**
 * The {@code XssSecurity} class represents xss security.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class XssSecurity {
    private static final String SCRIPT_REGEX = "</?script>";
    private static final String EMPTY_LINE = "";

    private XssSecurity() {
    }

    /**
     * Secure line.
     *
     * @param line the line
     * @return the string containing secured line
     */
    public static String secure(String line) {
        String securedLine = line;
        if (line != null) {
            securedLine = line.replaceAll(SCRIPT_REGEX, EMPTY_LINE);
        }
        return securedLine;
    }
}
