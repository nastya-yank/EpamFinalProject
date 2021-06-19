package com.golubeva.project.dao;

import com.golubeva.project.entity.CustomImage;
import com.golubeva.project.exception.DaoException;
import java.sql.Connection;

/**
 * The {@code ImageDao} interface represents image dao.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface ImageDao {

    /**
     * Add image.
     *
     * @param image the image
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(CustomImage image, Connection connection) throws DaoException;
}
