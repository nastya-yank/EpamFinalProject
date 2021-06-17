package com.traulko.project.dao;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDao} interface represents order dao.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface OrderDao {

    /**
     * Add order.
     *
     * @param order the order
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(CustomOrder order, Connection connection) throws DaoException;

    /**
     * Remove order.
     *
     * @param orderId the order index
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(Integer orderId, Connection connection) throws DaoException;

    /**
     * Find orders by search query.
     *
     * @param searchQuery the search query
     * @return the list of custom orders
     * @throws DaoException the dao exception
     */
    List<CustomOrder> findBySearchQuery(String searchQuery) throws DaoException;

    /**
     * Produce order.
     *
     * @param orderId the order index
     * @param date the local date
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean produce(Integer orderId, LocalDate date) throws DaoException;

    /**
     * Reject order.
     *
     * @param orderId the order index
     * @param date the local date
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean reject(Integer orderId, LocalDate date) throws DaoException;

    /**
     * Find orders by user id.
     *
     * @param userId the user id
     * @return the list of custom orders
     * @throws DaoException the dao exception
     */
    List<CustomOrder> findOrdersByUserId(Integer userId) throws DaoException;

    /**
     * Find all orders.
     *
     * @return the list of custom orders
     * @throws DaoException the dao exception
     */
    List<CustomOrder> findAll() throws DaoException;

    /**
     * Find order by id.
     *
     * @return the optional of custom order
     * @throws DaoException the dao exception
     */
    Optional<CustomOrder> findById(Integer id) throws DaoException;
}
