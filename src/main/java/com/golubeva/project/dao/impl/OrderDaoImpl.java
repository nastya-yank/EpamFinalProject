package com.golubeva.project.dao.impl;

import com.golubeva.project.dao.OrderDao;
import com.golubeva.project.dao.connection.ConnectionPool;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.ConnectionDatabaseException;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.dao.ColumnName;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDaoImpl} class represents order dao implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl INSTANCE = new OrderDaoImpl();
    private static final String ADD_ORDER = "INSERT INTO orders (order_creation_date, order_closing_date," +
            "order_status, user_id_fk) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk where user_id = ?";
    private static final String FIND_BY_ID = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk where order_id = ?";
    private static final String FIND_ALL = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk";
    private static final String FIND_PRODUCTS_BY_SEARCH_QUERY = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk where concat(order_id, order_creation_date, order_closing_date, order_status, user_id) like ?";
    private static final String PRODUCE_ORDER = "UPDATE orders SET order_status = \"PRODUCED\", " +
            "order_closing_date = ? where order_id = ?";
    private static final String REJECT_ORDER = "UPDATE orders SET order_status = \"DENIED\", " +
            "order_closing_date = ? where order_id = ?";
    private static final String REMOVE_ORDER = "DELETE FROM orders where order_id = ?";
    private static final String PERCENT = "%";

    private OrderDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OrderDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomOrder order, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            Date creationDate = Date.valueOf(order.getCreationDate());
            statement.setLong(1, creationDate.getTime());
            Date closingDate = Date.valueOf(order.getCreationDate());
            statement.setLong(2, closingDate.getTime());
            statement.setString(3, order.getStatus().toString());
            statement.setInt(4, order.getUser().getUserId());
            boolean result = statement.executeUpdate() > 0;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding order", e);
        }
    }

    @Override
    public boolean remove(Integer orderId, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_ORDER)) {
            statement.setInt(1, orderId);
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while removing order with order id: " + orderId, e);
        }
    }

    @Override
    public List<CustomOrder> findBySearchQuery(String searchQuery) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_SEARCH_QUERY)) {
            statement.setString(1, PERCENT + searchQuery + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<CustomOrder> orderList = new ArrayList<>();
            while (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding orders by search query error", e);
        }
    }

    @Override
    public boolean produce(Integer orderId, LocalDate date) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(PRODUCE_ORDER)) {
            Date closingDate = Date.valueOf(date);
            statement.setLong(1, closingDate.getTime());
            statement.setInt(2, orderId);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Produce order error", e);
        }
    }

    @Override
    public boolean reject(Integer orderId, LocalDate date) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REJECT_ORDER)) {
            Date closingDate = Date.valueOf(date);
            statement.setLong(1, closingDate.getTime());
            statement.setInt(2, orderId);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Reject order error", e);
        }
    }

    @Override
    public List<CustomOrder> findOrdersByUserId(Integer userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<CustomOrder> orderList = new ArrayList<>();
            while (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding orders", e);
        }
    }

    @Override
    public List<CustomOrder> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<CustomOrder> orderList = new ArrayList<>();
            while (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding all orders", e);
        }
    }

    @Override
    public Optional<CustomOrder> findById(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<CustomOrder> orderOptional = Optional.empty();
            if (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding order by id error", e);
        }
    }

    private CustomOrder createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
        long creationDateLong = resultSet.getLong(ColumnName.ORDER_CREATION_DATE);
        Date creationDate = new Date(creationDateLong);
        long closingDateLong = resultSet.getLong(ColumnName.ORDER_CLOSING_DATE);
        Date closingDate = new Date(closingDateLong);
        CustomOrder.Status orderStatus = CustomOrder.Status.valueOf(
                resultSet.getString(ColumnName.ORDER_STATUS));
        Integer userId = Integer.parseInt(resultSet.getString(ColumnName.USER_ID));
        User user = new User();
        user.setUserId(userId);
        return new CustomOrder(id, creationDate.toLocalDate(), closingDate.toLocalDate(),
                orderStatus, user);
    }
}
