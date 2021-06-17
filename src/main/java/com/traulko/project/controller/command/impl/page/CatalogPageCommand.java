package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.ProductService;
import com.traulko.project.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code CatalogPageCommand} class represents browse catalog page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CatalogPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(CatalogPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<Product> productList = productService.findAllProducts();
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            page = PagePath.CATALOG;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding all products", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
