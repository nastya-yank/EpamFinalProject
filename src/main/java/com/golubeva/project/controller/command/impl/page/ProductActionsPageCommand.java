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
import java.util.Optional;

/**
 * The {@code ProductActionsPageCommand} class represents browse product actions page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProductActionsPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(ProductActionsPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        try {
            Optional<Product> optionalProduct = productService.findProductById(id);
            if (optionalProduct.isPresent()) {
                request.setAttribute(RequestParameter.PRODUCT, optionalProduct.get());
            }
            page = PagePath.PRODUCT_ACTIONS;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding all users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
