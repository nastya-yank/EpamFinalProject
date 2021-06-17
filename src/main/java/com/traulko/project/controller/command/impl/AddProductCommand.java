package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.ProductService;
import com.traulko.project.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code AddProductCommand} class represents add product command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class AddProductCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String title = request.getParameter(RequestParameter.TITLE);
        String price = request.getParameter(RequestParameter.PRICE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String photoName = (String) request.getAttribute(RequestParameter.PHOTO_NAME);
        try {
            if (productService.addProduct(title, price, description, photoName)) {
                request.setAttribute(RequestParameter.ADD_PRODUCT_SUCCESS, true);
            } else {
                request.setAttribute(RequestParameter.ADD_PRODUCT_ERROR, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while adding product", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
