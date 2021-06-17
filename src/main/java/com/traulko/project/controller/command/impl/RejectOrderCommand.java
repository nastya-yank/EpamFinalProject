package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code RejectOrderCommand} class represents reject order command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class RejectOrderCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(RejectOrderCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (orderService.rejectOrder(orderId)) {
                request.setAttribute(RequestParameter.ORDER_REJECT_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.ORDER_REJECT_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while reject order", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
