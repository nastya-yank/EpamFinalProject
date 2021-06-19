package com.golubeva.project.dao;

import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.entity.OrderItem;
import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 * The {@code OrderItemDao} interface represents order item dao.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface OrderItemDao {

    /**
     * Add order item.
     *
     * @param order the order
     * @param product the product
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(CustomOrder order, Product product, Connection connection) throws DaoException;

    /**
     * Remove order item.
     *
     * @param order the order
     * @param product the product
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(CustomOrder order, Product product, Connection connection) throws DaoException;

    /**
     * Remove all order items.
     *
     * @param orderId the order index
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean removeAll(Integer orderId, Connection connection) throws DaoException;

    /**
     * Find order items by order id.
     *
     * @param orderId the order index
     * @return the list of order items
     * @throws DaoException the dao exception
     */
    List<OrderItem> findOrderItemsByOrderId(Integer orderId) throws DaoException;
}
