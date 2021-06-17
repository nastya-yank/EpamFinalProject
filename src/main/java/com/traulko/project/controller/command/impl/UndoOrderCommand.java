package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.UserBasketProductService;
import com.traulko.project.service.impl.OrderServiceImpl;
import com.traulko.project.service.impl.UserBasketProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code UndoOrderCommand} class represents undo order command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UndoOrderCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(CreateOrderCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();
    private static final UserBasketProductService basketService = new UserBasketProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (orderService.removeOrder(orderId)) {
                request.setAttribute(RequestParameter.ORDER_UNDO_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.ORDER_UNDO_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while order undo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
