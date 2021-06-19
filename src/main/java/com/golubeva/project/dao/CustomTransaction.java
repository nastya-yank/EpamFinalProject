package com.golubeva.project.dao;

import com.golubeva.project.dao.connection.ConnectionPool;
import com.golubeva.project.dao.impl.ImageDaoImpl;
import com.golubeva.project.dao.impl.OrderDaoImpl;
import com.golubeva.project.dao.impl.OrderItemDaoImpl;
import com.golubeva.project.dao.impl.ProductDaoImpl;
import com.golubeva.project.dao.impl.UserBasketProductDaoImpl;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.entity.Product;
import com.golubeva.project.entity.UserBasketProduct;
import com.golubeva.project.exception.ConnectionDatabaseException;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The {@code CustomTransaction} class represents custom transaction.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CustomTransaction {
    private static final CustomTransaction INSTANCE = new CustomTransaction();
    private static final Logger LOGGER = LogManager.getLogger(CustomTransaction.class);

    private CustomTransaction() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CustomTransaction getInstance() {
        return INSTANCE;
    }

    /**
     * Add order and order item transaction.
     *
     * @param order the order
     * @param userBasketProductList the user basket product list
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean addOrderAndOrderItems(CustomOrder order, List<UserBasketProduct> userBasketProductList) throws TransactionException {
        Connection connection = null;
        UserBasketProductDao userBasketProductDao = UserBasketProductDaoImpl.getInstance();
        OrderDao orderDao = OrderDaoImpl.getInstance();
        OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = orderDao.add(order, connection);
            if (result) {
                for (UserBasketProduct userBasketProduct : userBasketProductList) {
                    if (!(orderItemDao.add(order, userBasketProduct.getProduct(), connection)
                            && userBasketProductDao.remove(userBasketProduct))) {
                        result = false;
                    }
                }
            }
            connection.commit();
            return result;
        } catch (ConnectionDatabaseException | SQLException | DaoException e) {
            rollback(connection);
            throw new TransactionException("Error while adding order and orderItem " + order, e);
        } finally {
            close(connection);
        }
    }

    /**
     * Remove order and order items transaction.
     *
     * @param orderId the order index
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean removeOrderAndOrderItems(Integer orderId) throws TransactionException {
        Connection connection = null;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = orderItemDao.removeAll(orderId, connection);
            if (result) {
                if (!orderDao.remove(orderId, connection)) {
                    result = false;
                }
            }
            connection.commit();
            return result;
        } catch (ConnectionDatabaseException | SQLException | DaoException e) {
            rollback(connection);
            throw new TransactionException("Error while adding order and orderItem with order id" + orderId, e);
        } finally {
            close(connection);
        }
    }

    /**
     * Add product and image transaction.
     *
     * @param product the product
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean addProductAndImage(Product product) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        ProductDao productDao = ProductDaoImpl.getInstance();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(product.getImage(), connection);
            if (result) {
                result = productDao.add(product, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionDatabaseException | SQLException | DaoException e) {
            rollback(connection);
            throw new TransactionException("Error while adding product and image " + product, e);
        } finally {
            close(connection);
        }
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Rollback error");
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Close connection error");
            }
        }
    }
}
