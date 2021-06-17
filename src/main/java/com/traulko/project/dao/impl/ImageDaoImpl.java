package com.traulko.project.dao.impl;

import com.traulko.project.dao.ImageDao;
import com.traulko.project.entity.CustomImage;
import com.traulko.project.exception.DaoException;

import java.sql.*;

/**
 * The {@code ImageDaoImpl} class represents image dao implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ImageDaoImpl implements ImageDao {
    private static final ImageDaoImpl INSTANCE = new ImageDaoImpl();
    private static final String ADD_IMAGE = "INSERT INTO images (image_name) VALUES (?)";

    private ImageDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ImageDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomImage image, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_IMAGE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, image.getName());
            boolean result = statement.executeUpdate() > 0;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                image.setImageId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding image", e);
        }
    }
}
