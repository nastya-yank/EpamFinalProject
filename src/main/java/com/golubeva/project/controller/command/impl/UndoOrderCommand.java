package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.OrderService;
import com.golubeva.project.service.UserBasketProductService;
import com.golubeva.project.service.impl.OrderServiceImpl;
import com.golubeva.project.service.impl.UserBasketProductServiceImpl;
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
