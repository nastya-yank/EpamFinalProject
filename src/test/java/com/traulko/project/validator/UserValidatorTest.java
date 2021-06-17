package com.traulko.project.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
    @DataProvider(name = "isIdValidPositiveData")
    public Object[][] createValidIdPositiveData() {
        return new Object[][]{
                {"1"},
                {"1054"},
                {"123456789"}
        };
    }

    @Test(dataProvider = "isIdValidPositiveData")
    public void isIdValidPositiveTest(String id) {
        boolean actual = UserValidator.isIdValid(id);
        assertTrue(actual);
    }

    @DataProvider(name = "isIdValidNegativeData")
    public Object[][] createValidIdNegativeData() {
        return new Object[][]{
                {"-1"},
                {"0"},
                {"1231231231233"},
                {null},
                {""},
                {"   "}
        };
    }

    @Test(dataProvider = "isIdValidNegativeData")
    public void isIdValidNegativeTest(String id) {
        boolean actual = UserValidator.isIdValid(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isPasswordValidPositiveData")
    public Object[][] createValidPasswordPositiveData() {
        return new Object[][]{
                {"qwert1"},
                {"123456789qwerty"},
                {"12345y"}
        };
    }

    @Test(dataProvider = "isPasswordValidPositiveData")
    public void isPasswordValidPositiveTest(String password) {
        boolean actual = UserValidator.isPasswordValid(password);
        assertTrue(actual);
    }

    @DataProvider(name = "isPasswordValidNegativeData")
    public Object[][] createValidPasswordNegativeData() {
        return new Object[][]{
                {"-.,!#$&%^*1a"},
                {"0"},
                {"1234567890qwerty1"},
                {null},
                {""},
                {"   "}
        };
    }

    @Test(dataProvider = "isPasswordValidNegativeData")
    public void isPasswordValidNegativeTest(String password) {
        boolean actual = UserValidator.isPasswordValid(password);
        assertFalse(actual);
    }

    @DataProvider(name = "isEmailValidPositiveData")
    public Object[][] createValidEmailPositiveData() {
        return new Object[][]{
                {"qwer1@mail.ru"},
                {"1@gmail.com"},
                {"yana.politaeva@tut.by"}
        };
    }

    @Test(dataProvider = "isEmailValidPositiveData")
    public void isEmailValidPositiveTest(String email) {
        boolean actual = UserValidator.isEmailValid(email);
        assertTrue(actual);
    }

    @DataProvider(name = "isEmailValidNegativeData")
    public Object[][] createValidEmailNegativeData() {
        return new Object[][]{
                {",.@gmail.com"},
                {"0"},
                {"123@mailru"},
                {"123mail.ru"},
                {null},
                {""},
                {"   "}
        };
    }

    @Test(dataProvider = "isEmailValidNegativeData")
    public void isEmailValidNegativeTest(String email) {
        boolean actual = UserValidator.isEmailValid(email);
        assertFalse(actual);
    }

    @DataProvider(name = "isPriceValidPositiveData")
    public Object[][] createValidPricePositiveData() {
        return new Object[][]{
                {"100.1"},
                {"0.01"},
                {"9999.9"},
                {"0.0"}
        };
    }

    @Test(dataProvider = "isPriceValidPositiveData")
    public void isPriceValidPositiveTest(String price) {
        boolean actual = ProductValidator.isPriceValid(price);
        assertTrue(actual);
    }

    @DataProvider(name = "isPriceValidNegativeData")
    public Object[][] createValidPriceNegativeData() {
        return new Object[][]{
                {"-1"},
                {"sad"},
                {"null"},
                {"   "},
                {null},
                {"999999.99"},
                {"9999.999"}
        };
    }

    @Test(dataProvider = "isPriceValidNegativeData")
    public void isPriceValidNegativeTest(String price) {
        boolean actual = ProductValidator.isPriceValid(price);
        assertFalse(actual);
    }
}