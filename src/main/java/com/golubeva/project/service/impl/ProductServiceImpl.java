package com.golubeva.project.service.impl;

import com.golubeva.project.entity.CustomImage;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.exception.TransactionException;
import com.golubeva.project.service.ProductService;
import com.golubeva.project.validator.ProductValidator;
import com.golubeva.project.dao.CustomTransaction;
import com.golubeva.project.dao.ProductDao;
import com.golubeva.project.dao.impl.ProductDaoImpl;
import com.golubeva.project.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductServiceImpl} class represents product service implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final CustomTransaction customTransaction = CustomTransaction.getInstance();

    @Override
    public boolean addProduct(String productTitle, String price, String description, String imageName) throws ServiceException {
        boolean result = false;
        try {
            if (ProductValidator.isTitleValid(productTitle) && ProductValidator.isPriceValid(price) &&
                    ProductValidator.isDescriptionValid(description)) {
                Product product = new Product();
                product.setTitle(productTitle);
                product.setDescription(description);
                product.setPrice(Double.parseDouble(price));
                CustomImage image = new CustomImage();
                image.setName(imageName);
                product.setImage(image);
                result = customTransaction.addProductAndImage(product);
            }
        } catch (TransactionException e) {
            throw new ServiceException("Error while adding product", e);
        }
        return result;
    }

    @Override
    public boolean updateProduct(String productId, String productTitle, String price, String description) throws ServiceException {
        boolean result = false;
        try {
            if (ProductValidator.isIdValid(productId) && ProductValidator.isTitleValid(productTitle) && ProductValidator.isPriceValid(price) &&
                    ProductValidator.isDescriptionValid(description)) {
                int productIdParsed = Integer.parseInt(productId);
                Optional<Product> optionalProduct = productDao.findById(productIdParsed);
                if (optionalProduct.isPresent()) {
                    Product product = optionalProduct.get();
                    product.setTitle(productTitle);
                    product.setDescription(description);
                    product.setPrice(Double.parseDouble(price));
                    result = productDao.update(product);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while updating product", e);
        }
        return result;
    }

    @Override
    public Optional<Product> findProductById(String id) throws ServiceException {
        Optional<Product> optionalProduct = Optional.empty();
        try {
            if (ProductValidator.isIdValid(id)) {
                int productId = Integer.parseInt(id);
                optionalProduct = productDao.findById(productId);
            }
            return optionalProduct;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding product by id", e);
        }
    }

    @Override
    public List<Product> findBySearchQuery(String searchQuery) throws ServiceException {
        try {
            return productDao.findBySearchQuery(searchQuery);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding products by search query", e);
        }
    }

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        try {
            return productDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all products", e);
        }
    }
}
