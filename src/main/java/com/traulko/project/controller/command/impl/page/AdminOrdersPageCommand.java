package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code AdminOrdersPageCommand} class represents browse admin orders page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminOrdersPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(PersonalAccountPageCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<CustomOrder> orderList = orderService.findAllOrders();
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
