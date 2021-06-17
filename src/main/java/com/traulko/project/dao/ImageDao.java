package com.traulko.project.dao;

import com.traulko.project.entity.CustomImage;
import com.traulko.project.exception.DaoException;

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
