package com.golubeva.project.service.impl;

import com.golubeva.project.dao.UserBasketProductDao;
import com.golubeva.project.dao.impl.UserBasketProductDaoImpl;
import com.golubeva.project.entity.Product;
import com.golubeva.project.entity.UserBasketProduct;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserBasketProductService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserBasketProductServiceImplTest {
    private UserBasketProductDao userBasketProductDao;
    private UserBasketProductService userBasketProductService;

    @BeforeClass
    public void setUp() {
        userBasketProductDao = mock(UserBasketProductDaoImpl.class);
        Whitebox.setInternalState(UserBasketProductDaoImpl.class, "INSTANCE", userBasketProductDao);
        userBasketProductService = new UserBasketProductServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        userBasketProductDao = null;
        userBasketProductService = null;
    }

    @Test
    public void addPositiveTest() {
        try {
            when(userBasketProductDao.add(any(UserBasketProduct.class))).thenReturn(true);
            boolean actual = userBasketProductService.addUserBasketProduct("1", "2");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addNegativeTest() {
        try {
            when(userBasketProductDao.add(any(UserBasketProduct.class))).thenReturn(true);
            boolean actual = userBasketProductService.addUserBasketProduct(" ", null);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void calculateTotalPricePositiveTest() {
        UserBasketProduct userBasketProduct = new UserBasketProduct();
        Product product = new Product();
        product.setPrice(100);
        userBasketProduct.setProduct(product);
        List<UserBasketProduct> userBasketProductList = new ArrayList<>();
        userBasketProductList.add(userBasketProduct);
        double expected = 100;
        double actual = userBasketProductService.calculateTotalPrice(userBasketProductList);
        assertEquals(actual, expected, 0.1);
    }

    @Test
    public void calculateTotalPriceNegativeTest() {
        UserBasketProduct userBasketProduct = new UserBasketProduct();
        Product product = new Product();
        product.setPrice(100);
        userBasketProduct.setProduct(product);
        List<UserBasketProduct> userBasketProductList = new ArrayList<>();
        userBasketProductList.add(userBasketProduct);
        double expected = 101;
        double actual = userBasketProductService.calculateTotalPrice(userBasketProductList);
        assertNotEquals(actual, expected, 0.1);
    }

    @Test
    public void removePositiveTest() {
        try {
            when(userBasketProductDao.remove(any(UserBasketProduct.class))).thenReturn(true);
            boolean actual = userBasketProductService.removeUserBasketProduct("1", "2");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removeNegativeTest() {
        try {
            when(userBasketProductDao.remove(any(UserBasketProduct.class))).thenReturn(true);
            boolean actual = userBasketProductService.removeUserBasketProduct(" ", null);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void getUserBasketProductsByUserIdPositiveTest() {
        try {
            when(userBasketProductDao.findBasketProductsByUserId(any(Integer.class)))
                    .thenReturn(new ArrayList<>());
            List<UserBasketProduct> actual = userBasketProductService.findUserBasketProductsByUserId("1");
            List<UserBasketProduct> expected = new ArrayList<>();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void getUserBasketProductsByUserIdNegativeTest() {
        try {
            List<UserBasketProduct> expected = null;
            when(userBasketProductDao.findBasketProductsByUserId(any(Integer.class)))
                    .thenReturn(new ArrayList<>());
            List<UserBasketProduct> actual = userBasketProductService.findUserBasketProductsByUserId("");
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }
}