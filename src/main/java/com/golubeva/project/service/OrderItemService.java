package com.golubeva.project.service;

import com.golubeva.project.entity.OrderItem;
import com.golubeva.project.exception.ServiceException;

import java.util.List;

/**
 * The {@code OrderItemService} service represents order item service.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface OrderItemService {

    /**
     * Find order items by order id.
     *
     * @param orderId the order index
     * @return the list of order items
     * @throws ServiceException the service exception
     */
    List<OrderItem> findOrderItemsByOrderId(String orderId) throws ServiceException;
}
