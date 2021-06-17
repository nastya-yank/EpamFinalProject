package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserBasketProductService;
import com.traulko.project.service.impl.UserBasketProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code AddProductToBasketCommand} class represents add product to basket command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class AddProductToBasketCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserBasketProductService basketService = new UserBasketProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        try {
            if (basketService.addUserBasketProduct(userId, productId)) {
                request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_SUCCESS, true);
            } else {
                request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_ERROR, true);
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
