package com.golubeva.project.validator;

/**
 * The {@code OrderItemValidator} class represents order item validator.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderItemValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";

    /**
     * Check id for valid.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
