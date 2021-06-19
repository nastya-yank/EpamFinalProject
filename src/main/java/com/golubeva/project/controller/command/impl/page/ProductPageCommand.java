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
 * The {@code ProductPageCommand} class represents browse product page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProductPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(ProductPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        String page;
        try {
            Optional<Product> optionalProduct = productService.findProductById(id);
            if (optionalProduct.isPresent()) {
                request.setAttribute(RequestParameter.PRODUCT, optionalProduct.get());
                page = PagePath.PRODUCT;
            } else {
                request.setAttribute(RequestParameter.PRODUCT_FIND_ERROR, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding all users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
