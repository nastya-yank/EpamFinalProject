package com.traulko.project.service.impl;

import com.traulko.project.dao.OrderItemDao;
import com.traulko.project.dao.impl.OrderItemDaoImpl;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderItemService;
import com.traulko.project.validator.OrderItemValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OrderItemServiceImpl} class represents order item service implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();

    @Override
    public List<OrderItem> findOrderItemsByOrderId(String id) throws ServiceException {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            if (OrderItemValidator.isIdValid(id)) {
                int orderId = Integer.parseInt(id);
                orderItemList = orderItemDao.findOrderItemsByOrderId(orderId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding order items by order id", e);
        }
        return orderItemList;
    }
}
