package com.golubeva.project.service;

import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.ServiceException;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductService} service represents product service.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface ProductService {

    /**
     * Add product.
     *
     * @param productTitle the product title
     * @param description the description
     * @param price the price
     * @param imageName the image name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addProduct(String productTitle, String price, String description, String imageName) throws ServiceException;

    /**
     * Update product.
     *
     * @param productTitle the product title
     * @param description the description
     * @param price the price
     * @param productId the image name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateProduct(String productId, String productTitle, String price, String description) throws ServiceException;

    /**
     * Find product by id.
     *
     * @param productId the product index
     * @return the optional of product
     * @throws ServiceException the service exception
     */
    Optional<Product> findProductById(String productId) throws ServiceException;

    /**
     * Find orders by search query.
     *
     * @param searchQuery the search query
     * @return the list of products
     * @throws ServiceException the service exception
     */
    List<Product> findBySearchQuery(String searchQuery) throws ServiceException;

    /**
     * Find all products.
     *
     * @return the list of products
     * @throws ServiceException the service exception
     */
    List<Product> findAllProducts() throws ServiceException;
}
