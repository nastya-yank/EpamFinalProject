package com.traulko.project.service;

import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;

/**
 * The {@code UserBasketProductService} service represents user basket product service.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface UserBasketProductService {

    /**
     * Add user basket product.
     *
     * @param userId the user index
     * @param productId the product index
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addUserBasketProduct(String userId, String productId) throws ServiceException;

    /**
     * Calculate total price of basket.
     *
     * @param userBasketProductList the user basket product list
     * @return the total price
     * @throws ServiceException the service exception
     */
    double calculateTotalPrice(List<UserBasketProduct> userBasketProductList);

    /**
     * Remove user basket product.
     *
     * @param userId the user index
     * @param productId the product index
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeUserBasketProduct(String userId, String productId) throws ServiceException;

    /**
     * Find orders by search query.
     *
     * @param userId the user index
     * @return the list of user basket products
     * @throws ServiceException the service exception
     */
    List<UserBasketProduct> findUserBasketProductsByUserId(String userId) throws ServiceException;
}
