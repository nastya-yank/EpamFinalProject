package com.traulko.project.service.impl;

import com.traulko.project.dao.CustomTransaction;
import com.traulko.project.dao.OrderDao;
import com.traulko.project.dao.impl.OrderDaoImpl;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.User;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.exception.TransactionException;
import com.traulko.project.service.OrderService;
import com.traulko.project.validator.OrderValidator;
import com.traulko.project.validator.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderServiceImpl} class represents order service implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final CustomTransaction customTransaction = CustomTransaction.getInstance();

    @Override
    public boolean addOrder(String userId, List<UserBasketProduct> userBasketProductList) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isIdValid(userId) && userBasketProductList != null) {
                int userIdParsed = Integer.parseInt(userId);
                CustomOrder order = new CustomOrder();
                LocalDate date = LocalDate.now();
                order.setCreationDate(date);
                order.setClosingDate(date);
                order.setStatus(CustomOrder.Status.UNDER_CONSIDERATION);
                User user = new User();
                user.setUserId(userIdParsed);
                order.setUser(user);
                result = customTransaction.addOrderAndOrderItems(order, userBasketProductList);
            }
        } catch (TransactionException e) {
            throw new ServiceException("Error while adding order", e);
        }
        return result;
    }

    @Override
    public boolean removeOrder(String orderId) throws ServiceException {
        boolean result = false;
        try {
            if (OrderValidator.isIdValid(orderId)) {
                int orderIdParsed = Integer.parseInt(orderId);
                result = customTransaction.removeOrderAndOrderItems(orderIdParsed);
            }
        } catch (TransactionException e) {
            throw new ServiceException("Error while removing order", e);
        }
        return result;
    }

    @Override
    public List<CustomOrder> findOrdersByUserId(String id) throws ServiceException {
        List<CustomOrder> orderList = new ArrayList<>();
        try {
            if (UserValidator.isIdValid(id)) {
                int userId = Integer.parseInt(id);
                orderList = orderDao.findOrdersByUserId(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding orders by user id", e);
        }
        return orderList;
    }

    @Override
    public List<CustomOrder> findOrdersBySearchQuery(String searchQuery) throws ServiceException {
        try {
            return orderDao.findBySearchQuery(searchQuery);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding orders by search query", e);
        }
    }

    @Override
    public List<CustomOrder> findAllOrders() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all orders", e);
        }
    }

    @Override
    public boolean produceOrder(String orderId) throws ServiceException {
        boolean isProduced = false;
        try {
            if (OrderValidator.isIdValid(orderId)) {
                LocalDate date = LocalDate.now();
                int orderIdParsed = Integer.parseInt(orderId);
                isProduced = orderDao.produce(orderIdParsed, date);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while order produce", e);
        }
        return isProduced;
    }

    @Override
    public boolean rejectOrder(String orderId) throws ServiceException {
        boolean isRejected = false;
        try {
            if (OrderValidator.isIdValid(orderId)) {
                LocalDate date = LocalDate.now();
                int orderIdParsed = Integer.parseInt(orderId);
                isRejected = orderDao.reject(orderIdParsed, date);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while order reject", e);
        }
        return isRejected;
    }

    @Override
    public Optional<CustomOrder> findOrderById(String id) throws ServiceException {
        Optional<CustomOrder> orderOptional = Optional.empty();
        try {
            if (OrderValidator.isIdValid(id)) {
                int orderId = Integer.parseInt(id);
                orderOptional = orderDao.findById(orderId);
            }
            return orderOptional;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding order by id", e);
        }
    }
}
