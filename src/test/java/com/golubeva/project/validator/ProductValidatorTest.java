package com.golubeva.project.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProductValidatorTest {
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
        boolean actual = ProductValidator.isIdValid(id);
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
        boolean actual = ProductValidator.isIdValid(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isTitleValidPositiveData")
    public Object[][] createValidTitlePositiveData() {
        return new Object[][]{
                {"Car"},
                {"Product"},
                {"New product"}
        };
    }

    @Test(dataProvider = "isTitleValidPositiveData")
    public void isTitleValidPositiveTest(String title) {
        boolean actual = ProductValidator.isTitleValid(title);
        assertTrue(actual);
    }

    @DataProvider(name = "isTitleValidNegativeData")
    public Object[][] createValidTitleNegativeData() {
        return new Object[][]{
                {""},
                {null},
                {"Product3"},
                {"   "},
                {"Продукт"},
                {"-Product"}
        };
    }

    @Test(dataProvider = "isTitleValidNegativeData")
    public void isTitleValidNegativeTest(String title) {
        boolean actual = ProductValidator.isIdValid(title);
        assertFalse(actual);
    }

    @DataProvider(name = "isDescriptionValidPositiveData")
    public Object[][] createValidDescriptionPositiveData() {
        return new Object[][]{
                {"Description"},
                {"New description"},
                {"New product"}
        };
    }

    @Test(dataProvider = "isDescriptionValidPositiveData")
    public void isDescriptionValidPositiveTest(String description) {
        boolean actual = ProductValidator.isDescriptionValid(description);
        assertTrue(actual);
    }

    @DataProvider(name = "isDescriptionValidNegativeData")
    public Object[][] createValidDescriptionNegativeData() {
        return new Object[][]{
                {""},
                {null},
                {"   "},
                {"-Description"},
                {"1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols1001symbols" +
                        "1001symbols1001symbols1001symbols"}
        };
    }

    @Test(dataProvider = "isDescriptionValidNegativeData")
    public void isDescriptionValidNegativeTest(String description) {
        boolean actual = ProductValidator.isDescriptionValid(description);
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