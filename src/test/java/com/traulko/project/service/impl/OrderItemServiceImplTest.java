package com.traulko.project.service.impl;

import com.traulko.project.dao.OrderItemDao;
import com.traulko.project.dao.impl.OrderItemDaoImpl;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderItemService;
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

public class OrderItemServiceImplTest {
    private OrderItemService orderItemService;
    private OrderItemDao orderItemDao;

    @BeforeClass
    public void setUp() {
        orderItemDao = mock(OrderItemDaoImpl.class);
        Whitebox.setInternalState(OrderItemDaoImpl.class, "INSTANCE", orderItemDao);
        orderItemService = new OrderItemServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        orderItemService = null;
        orderItemDao = null;
    }

    @Test
    public void findOrderItemsByOrderIdPositiveTest() {
        try {
            List<OrderItem> expected = new ArrayList<>();
            when(orderItemDao.findOrderItemsByOrderId(any(Integer.class))).thenReturn(new ArrayList<>());
            List<OrderItem> actual = orderItemService.findOrderItemsByOrderId("1");
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrderItemsByOrderIdNegativeTest() {
        try {
            List<OrderItem> expected = null;
            when(orderItemDao.findOrderItemsByOrderId(any(Integer.class))).thenReturn(new ArrayList<>());
            List<OrderItem> actual = orderItemService.findOrderItemsByOrderId("1");
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }
}