package com.golubeva.project.dao.impl;

import com.golubeva.project.dao.connection.ConnectionPool;
import com.golubeva.project.entity.CustomImage;
import com.golubeva.project.entity.UserBasketProduct;
import com.golubeva.project.exception.ConnectionDatabaseException;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.dao.ColumnName;
import com.golubeva.project.dao.UserBasketProductDao;
import com.golubeva.project.entity.Product;
import com.golubeva.project.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserBasketProductDaoImpl} class represents user basket product dao implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserBasketProductDaoImpl implements UserBasketProductDao {
    private static final UserBasketProductDaoImpl INSTANCE = new UserBasketProductDaoImpl();
    private static final String ADD_PRODUCT_TO_BASKET = "INSERT INTO basket (user_id_fk, product_id_fk) " +
            "VALUES (?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT basket_id, user_id, product_id, product_title, product_price," +
            "product_description, image_id, image_name FROM basket INNER JOIN products ON product_id_fk = product_id " +
            "INNER JOIN images ON image_id = image_id_fk INNER JOIN users ON user_id_fk = user_id where user_id = ?";
    private static final String REMOVE_PRODUCT_FROM_BASKET = "DELETE FROM basket where user_id_fk = ? AND product_id_fk = ? LIMIT 1";

    private UserBasketProductDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserBasketProductDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(UserBasketProduct userBasketProduct) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_TO_BASKET)) {
            statement.setInt(1, userBasketProduct.getUser().getUserId());
            statement.setInt(2, userBasketProduct.getProduct().getProductId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while adding basket: " + userBasketProduct, e);
        }
    }

    @Override
    public boolean remove(UserBasketProduct userBasketProduct) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_FROM_BASKET)) {
            statement.setInt(1, userBasketProduct.getUser().getUserId());
            statement.setInt(2, userBasketProduct.getProduct().getProductId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while removing basket: " + userBasketProduct, e);
        }
    }

    @Override
    public List<UserBasketProduct> findBasketProductsByUserId(Integer userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<UserBasketProduct> userBasketProductList = new ArrayList<>();
            while (resultSet.next()) {
                UserBasketProduct userBasketProduct = createBasketProductFromResultSet(resultSet);
                userBasketProductList.add(userBasketProduct);
            }
            return userBasketProductList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding baskets", e);
        }
    }

    private UserBasketProduct createBasketProductFromResultSet(ResultSet resultSet) throws SQLException {
        Integer basketId = resultSet.getInt(ColumnName.BASKET_ID);
        Integer userId = resultSet.getInt(ColumnName.USER_ID);
        Integer productId = resultSet.getInt(ColumnName.PRODUCT_ID);
        double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
        String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
        String description = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        User user = new User();
        user.setUserId(userId);
        CustomImage image = new CustomImage(imageId, imageName);
        Product product = new Product(productId, title, price, description, image);
        return new UserBasketProduct(basketId, user, product);
    }
}
