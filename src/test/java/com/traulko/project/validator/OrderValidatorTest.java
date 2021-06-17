package com.traulko.project.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OrderValidatorTest {
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
        boolean actual = OrderValidator.isIdValid(id);
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
        boolean actual = OrderValidator.isIdValid(id);
        assertFalse(actual);
    }
}