package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.ProductService;
import com.golubeva.project.service.impl.ProductServiceImpl;
import com.golubeva.project.util.XssSecurity;
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
