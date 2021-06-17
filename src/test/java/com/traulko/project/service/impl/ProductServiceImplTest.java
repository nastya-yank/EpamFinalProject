package com.traulko.project.service.impl;

import com.traulko.project.dao.CustomTransaction;
import com.traulko.project.dao.ProductDao;
import com.traulko.project.dao.impl.ProductDaoImpl;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.exception.TransactionException;
import com.traulko.project.service.ProductService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ProductServiceImplTest {
    private ProductDao productDao;
    private CustomTransaction transaction;
    private ProductService productService;

    @BeforeClass
    public void setUp() {
        productDao = mock(ProductDaoImpl.class);
        transaction = mock(CustomTransaction.class);
        Whitebox.setInternalState(ProductDaoImpl.class, "INSTANCE", productDao);
        Whitebox.setInternalState(CustomTransaction.class, "INSTANCE", transaction);
        productService = new ProductServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        productDao = null;
        transaction = null;
        productService = null;
    }

    @Test
    public void addPositiveTest() {
        try {
            when(transaction.addProductAndImage(any(Product.class))).thenReturn(true);
            boolean actual = productService.addProduct("title", "1.1", "description", "imageName");
            assertTrue(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addNegativeTest() {
        try {
            when(transaction.addProductAndImage(any(Product.class))).thenReturn(true);
            boolean actual = productService.addProduct(" ", null, null, "imageName");
            assertFalse(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void updatePositiveTest() {
        try {
            when(productDao.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
            when(productDao.update(any(Product.class))).thenReturn(true);
            boolean actual = productService.updateProduct("1", "title", "1.1", "description");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void updateNegativeTest() {
        try {
            when(productDao.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
            when(productDao.update(any(Product.class))).thenReturn(true);
            boolean actual = productService.updateProduct(" ", null, null, "description");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryPositiveTest() {
        try {
            List<Product> expected = new ArrayList<>();
            when(productDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<Product> actual = productService.findBySearchQuery("1");
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryNegativeTest() {
        try {
            List<Product> expected = null;
            when(productDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<Product> actual = productService.findBySearchQuery("1");
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdPositiveTest() {
        try {
            when(productDao.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
            Product expected = new Product();
            Optional<Product> actual = productService.findProductById("1");
            assertEquals(actual.get(), expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdNegativeTest() {
        try {
            Optional<Product> expected = Optional.of(new Product());
            when(productDao.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
            Optional<Product> actual = productService.findProductById(" ");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllPositiveTest() {
        try {
            List<Product> expected = new ArrayList<>();
            when(productDao.findAll()).thenReturn(new ArrayList<>());
            List<Product> actual = productService.findAllProducts();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllNegativeTest() {
        try {
            List<Product> expected = null;
            when(productDao.findAll()).thenReturn(new ArrayList<>());
            List<Product> actual = productService.findAllProducts();
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }
}