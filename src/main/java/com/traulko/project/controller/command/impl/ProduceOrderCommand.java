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
 * The {@code ProduceOrderCommand} class represents produce order command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ProduceOrderCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(ProduceOrderCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (orderService.produceOrder(orderId)) {
                request.setAttribute(RequestParameter.ORDER_PRODUCE_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.ORDER_PRODUCE_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while produce order", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
