package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserBasketProductService;
import com.golubeva.project.service.impl.UserBasketProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code DeleteProductFromBasketCommand} class represents delete product from basket command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class DeleteProductFromBasketCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserBasketProductService basketService = new UserBasketProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        try {
            if (basketService.removeUserBasketProduct(userId, productId)) {
                request.setAttribute(RequestParameter.REMOVE_PRODUCT_FROM_BASKET_SUCCESS, true);
            } else {
                request.setAttribute(RequestParameter.REMOVE_PRODUCT_FROM_BASKET_ERROR, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while removing product", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
