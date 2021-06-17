package com.traulko.project.validator;

/**
 * The {@code ProductValidator} class represents product validator.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProductValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String TITLE_REGEX = "^\\p{L}[^<>]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^\\p{L}[^<>]{1,1000}$";
    private static final String PRICE_REGEX = "^[0-9]\\d{0,4}(\\.\\d{0,2})?$";

    /**
     * Check id for valid.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    /**
     * Check title for valid.
     *
     * @param title the title
     * @return the boolean
     */
    public static boolean isTitleValid(String title) {
        return isStringCorrect(title, TITLE_REGEX) && !title.isBlank();
    }

    /**
     * Check description for valid.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX) && !description.isBlank();
    }

    /**
     * Check price for valid.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceValid(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
