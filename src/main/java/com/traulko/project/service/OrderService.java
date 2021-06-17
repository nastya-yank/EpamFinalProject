package com.traulko.project.service;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderService} service represents order service.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface OrderService {

    /**
     * Add order.
     *
     * @param userId the user index
     * @param userBasketProductList the user basket product list
     * @return the list of order items
     * @throws ServiceException the service exception
     */
    boolean addOrder(String userId, List<UserBasketProduct> userBasketProductList) throws ServiceException;

    /**
     * Remove order.
     *
     * @param orderId the order index
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeOrder(String orderId) throws ServiceException;

    /**
     * Find orders by user id.
     *
     * @param userId the user index
     * @return the list of CustomOrders
     * @throws ServiceException the service exception
     */
    List<CustomOrder> findOrdersByUserId(String userId) throws ServiceException;

    /**
     * Find orders by search query.
     *
     * @param searchQuery the search query
     * @return the list of CustomOrders
     * @throws ServiceException the service exception
     */
    List<CustomOrder> findOrdersBySearchQuery(String searchQuery) throws ServiceException;

    /**
     * Find all orders.
     *
     * @return the list of CustomOrders
     * @throws ServiceException the service exception
     */
    List<CustomOrder> findAllOrders() throws ServiceException;

    /**
     * Product order.
     *
     * @param orderId the order index
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean produceOrder(String orderId) throws ServiceException;

    /**
     * Reject order.
     *
     * @param orderId the order index
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean rejectOrder(String orderId) throws ServiceException;

    /**
     * Find order by id.
     *
     * @param orderId the order index
     * @return the optional of CustomOrder
     * @throws ServiceException the service exception
     */
    Optional<CustomOrder> findOrderById(String orderId) throws ServiceException;
}
