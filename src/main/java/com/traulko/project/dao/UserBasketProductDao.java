package com.traulko.project.dao;

import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.DaoException;

import java.util.List;

/**
 * The {@code UserBasketProductDao} interface represents user basket product dao.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface UserBasketProductDao {

    /**
     * Add user basket product.
     *
     * @param userBasketProduct the user basket product
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(UserBasketProduct userBasketProduct) throws DaoException;

    /**
     * Remove user basket product.
     *
     * @param userBasketProduct the user basket product
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(UserBasketProduct userBasketProduct) throws DaoException;

    /**
     * Find basket products by user id.
     *
     * @param userId the user index
     * @return the list of user basket products
     * @throws DaoException the dao exception
     */
    List<UserBasketProduct> findBasketProductsByUserId(Integer userId) throws DaoException;
}
