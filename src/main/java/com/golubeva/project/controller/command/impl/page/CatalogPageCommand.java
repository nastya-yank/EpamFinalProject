package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.Product;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.ProductService;
import com.golubeva.project.service.impl.ProductServiceImpl;
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
