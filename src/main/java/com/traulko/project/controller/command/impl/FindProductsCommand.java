package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.ProductService;
import com.traulko.project.service.impl.ProductServiceImpl;
import com.traulko.project.util.XssSecurity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code FindProductsCommand} class represents find products command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FindProductsCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FindProductsCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String searchQuery = request.getParameter(RequestParameter.SEARCH_PRODUCTS_QUERY);
        try {
            String searchQuerySecured = XssSecurity.secure(searchQuery);
            List<Product> productList = productService.findBySearchQuery(searchQuerySecured);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            page = PagePath.CATALOG;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
