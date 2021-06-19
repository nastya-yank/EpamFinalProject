package com.golubeva.project.dao.impl;

import com.golubeva.project.dao.ProductDao;
import com.golubeva.project.dao.connection.ConnectionPool;
import com.golubeva.project.entity.CustomImage;
import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.ConnectionDatabaseException;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.dao.ColumnName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductDaoImpl} class represents product dao implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl INSTANCE = new ProductDaoImpl();
    private static final String FIND_ALL = "SELECT product_id, product_title, product_price," +
            " product_description, image_id, image_name FROM products INNER JOIN images ON" +
            " products.image_id_fk = images.image_id";
    private static final String ADD_PRODUCT = "INSERT INTO products (product_title, product_price, "
            + "product_description, image_id_fk) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT product_id, product_title, product_price," +
            " product_description, image_id, image_name FROM products INNER JOIN images ON" +
            " products.image_id_fk = images.image_id where product_id = ?";
    private static final String PERCENT = "%";
    private static final String FIND_PRODUCTS_BY_SEARCH_QUERY = "SELECT product_id, product_title, product_price, " +
            "product_description, image_id, image_name FROM products INNER JOIN images ON " +
            "products.image_id_fk = images.image_id where concat(product_title, product_price) like ?";
    private static final String UPDATE_PRODUCT = "UPDATE products set product_title = ?, product_price = ?," +
            "product_description = ? where product_id = ?";

    private ProductDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ProductDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Product> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding all products error", e);
        }
    }

    @Override
    public Optional<Product> findById(Integer productId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            Optional<Product> productOptional = Optional.empty();
            if (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                productOptional = Optional.of(product);
            }
            return productOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding product by id error", e);
        }
    }

    @Override
    public List<Product> findBySearchQuery(String searchQuery) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_SEARCH_QUERY)) {
            statement.setString(1, PERCENT + searchQuery + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding products by search query error", e);
        }
    }

    @Override
    public boolean update(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getTitle());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getProductId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while updating product: " + product, e);
        }
    }

    @Override
    public boolean add(Product product, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getTitle());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getImage().getImageId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding product: " + product, e);
        }
    }

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
        String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
        double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
        String description = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        return new Product(id, title, price, description, new CustomImage(imageId, imageName));
    }
}
