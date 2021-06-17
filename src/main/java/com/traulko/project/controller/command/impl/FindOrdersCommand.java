package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderServiceImpl;
import com.traulko.project.util.XssSecurity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code FindOrdersCommand} class represents find orders command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FindOrdersCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FindProductsCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String searchQuery = request.getParameter(RequestParameter.SEARCH_ORDERS_QUERY);
        try {
            String searchQuerySecured = XssSecurity.secure(searchQuery);
            List<CustomOrder> orderList = orderService.findOrdersBySearchQuery(searchQuerySecured);
            request.setAttribute(RequestParameter.ORDERS, orderList);
            page = PagePath.ADMIN_ORDERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding orders", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
