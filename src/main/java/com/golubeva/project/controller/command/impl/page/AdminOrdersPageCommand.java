package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.OrderService;
import com.golubeva.project.service.impl.OrderServiceImpl;
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
