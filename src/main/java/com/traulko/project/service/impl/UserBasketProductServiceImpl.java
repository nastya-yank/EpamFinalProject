package com.traulko.project.service.impl;

import com.traulko.project.dao.UserBasketProductDao;
import com.traulko.project.dao.impl.UserBasketProductDaoImpl;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserBasketProductService;
import com.traulko.project.validator.UserBasketProductValidator;
import com.traulko.project.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserBasketProductServiceImpl} class represents user basket product service implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserBasketProductServiceImpl implements UserBasketProductService {
    private final UserBasketProductDao userBasketProductDao = UserBasketProductDaoImpl.getInstance();

    @Override
    public boolean addUserBasketProduct(String userId, String productId) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isIdValid(userId) &&
                    UserBasketProductValidator.isIdValid(productId)) {
                Integer productIdParsed = Integer.parseInt(productId);
                Integer userIdParsed = Integer.parseInt(userId);
                User user = new User();
                user.setUserId(userIdParsed);
                Product product = new Product();
                product.setProductId(productIdParsed);
                UserBasketProduct userBasketProduct = new UserBasketProduct();
                userBasketProduct.setUser(user);
                userBasketProduct.setProduct(product);
                result = userBasketProductDao.add(userBasketProduct);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while adding basket", e);
        }
        return result;
    }

    @Override
    public double calculateTotalPrice(List<UserBasketProduct> userBasketProductList) {
        double totalPrice = 0;
        for (UserBasketProduct userBasketProduct : userBasketProductList) {
            totalPrice += userBasketProduct.getProduct().getPrice();
        }
        double scale = Math.pow(10, 2);
        return Math.ceil(totalPrice * scale) / scale;
    }

    @Override
    public boolean removeUserBasketProduct(String userId, String productId) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isIdValid(userId) &&
                    UserBasketProductValidator.isIdValid(productId)) {
                Integer productIdValue = Integer.parseInt(productId);
                Integer userIdParsed = Integer.parseInt(userId);
                Product product = new Product();
                product.setProductId(productIdValue);
                User user = new User();
                user.setUserId(userIdParsed);
                UserBasketProduct userBasketProduct = new UserBasketProduct();
                userBasketProduct.setUser(user);
                userBasketProduct.setProduct(product);
                result = userBasketProductDao.remove(userBasketProduct);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while removing basket", e);
        }
        return result;
    }

    @Override
    public List<UserBasketProduct> findUserBasketProductsByUserId(String id) throws ServiceException {
        List<UserBasketProduct> userBasketProductList = new ArrayList<>();
        try {
            if (UserBasketProductValidator.isIdValid(id)) {
                int userId = Integer.parseInt(id);
                userBasketProductList = userBasketProductDao.findBasketProductsByUserId(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding baskets", e);
        }
        return userBasketProductList;
    }
}