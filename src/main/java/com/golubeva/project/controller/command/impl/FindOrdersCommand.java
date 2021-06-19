package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.OrderService;
import com.golubeva.project.service.impl.OrderServiceImpl;
import com.golubeva.project.util.XssSecurity;
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
