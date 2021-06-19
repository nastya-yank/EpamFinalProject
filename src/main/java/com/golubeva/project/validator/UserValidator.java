package com.golubeva.project.validator;

import com.golubeva.project.controller.RequestParameter;
import java.util.Map;

/**
 * The {@code UserValidator} class represents user validator.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final String EMPTY_VALUE = "";

    /**
     * Check registration parameters for correct.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isEmailValid(parameters.get(RequestParameter.EMAIL))) {
            isCorrect = false;
            parameters.put(RequestParameter.EMAIL, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.NAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.NAME, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.SURNAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.SURNAME, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.PATRONYMIC))) {
            isCorrect = false;
            parameters.put(RequestParameter.PATRONYMIC, EMPTY_VALUE);
        }
        if (!isPasswordValid(parameters.get(RequestParameter.PASSWORD)) ||
                !(parameters.get(RequestParameter.PASSWORD)
                        .equals(parameters.get(RequestParameter.PASSWORD_REPEAT)))) {
            isCorrect = false;
            parameters.put(RequestParameter.PASSWORD, EMPTY_VALUE);
            parameters.put(RequestParameter.PASSWORD_REPEAT, EMPTY_VALUE);
        }
        return isCorrect;
    }

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
     * Check password for valid.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        return isStringCorrect(password, PASSWORD_REGEX) && !password.isBlank();
    }

    /**
     * Check email for valid.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        return isStringCorrect(email, EMAIL_REGEX) && !email.isBlank();
    }

    /**
     * Check name for valid.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
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
