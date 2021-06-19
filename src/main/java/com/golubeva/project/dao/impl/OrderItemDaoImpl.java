package com.golubeva.project.dao.impl;

import com.golubeva.project.dao.connection.ConnectionPool;
import com.golubeva.project.entity.CustomImage;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.entity.OrderItem;
import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.ConnectionDatabaseException;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.dao.ColumnName;
import com.golubeva.project.dao.OrderItemDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OrderItemDaoImpl} class represents orderItem dao implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderItemDaoImpl implements OrderItemDao {
    private static final OrderItemDaoImpl INSTANCE = new OrderItemDaoImpl();
    private static final String ADD_ORDER_ITEM = "INSERT INTO order_item (product_id_fk, order_id_fk)" +
            " VALUES (?, ?)";
    private static final String REMOVE_ORDER_ITEM = "DELETE FROM order_item where product_id_fk = ?" +
            ", order_id_fk = ?";
    private static final String REMOVE_ALL_ORDER_ITEMS = "DELETE FROM order_item where " +
            "order_id_fk = ?";
    private static final String FIND_BY_ORDER_ID = "SELECT order_item_id, product_id, product_title," +
            "product_price, product_description, image_id, image_name, order_id from order_item INNER JOIN products " +
            "ON product_id_fk = product_id INNER JOIN images ON image_id_fk = image_id INNER JOIN orders ON " +
            "order_id_fk = order_id where order_id = ?";

    private OrderItemDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OrderItemDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomOrder order, Product product, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER_ITEM)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, order.getOrderId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding orderItem: " + product, e);
        }
    }

    @Override
    public boolean remove(CustomOrder order, Product product, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_ORDER_ITEM)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, order.getOrderId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while removing orderItem: " + product, e);
        }
    }

    @Override
    public boolean removeAll(Integer orderId, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_ALL_ORDER_ITEMS)) {
            statement.setInt(1, orderId);
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while removing orderItems with order id: " + orderId, e);
        }
    }

    @Override
    public List<OrderItem> findOrderItemsByOrderId(Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ORDER_ID)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<OrderItem> orderItemList = new ArrayList<>();
            while (resultSet.next()) {
                OrderItem orderItem = createOrderItemFromResultSet(resultSet);
                orderItemList.add(orderItem);
            }
            return orderItemList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding baskets", e);
        }
    }

    private OrderItem createOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        Integer orderItemId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ITEM_ID));
        Integer productId = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
        String productTitle = resultSet.getString(ColumnName.PRODUCT_TITLE);
        double productPrice = resultSet.getLong(ColumnName.PRODUCT_PRICE);
        String productDescription = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        Integer orderId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
        CustomOrder order = new CustomOrder();
        order.setOrderId(orderId);
        CustomImage image = new CustomImage(imageId, imageName);
        Product product = new Product(productId, productTitle, productPrice, productDescription, image);
        return new OrderItem(orderItemId, product, order);
    }
}
