package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.CustomOrder;
import com.golubeva.project.entity.OrderItem;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.OrderItemService;
import com.golubeva.project.service.OrderService;
import com.golubeva.project.service.impl.OrderItemServiceImpl;
import com.golubeva.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderPageCommand} class represents browse order page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class OrderPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(OrderPageCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();
    private static final OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            Optional<CustomOrder> orderOptional = orderService.findOrderById(orderId);
            if (orderOptional.isPresent()) {
                List<OrderItem> orderItemList = orderItemService.findOrderItemsByOrderId(orderId);
                request.setAttribute(RequestParameter.ORDER_ITEMS, orderItemList);
                request.setAttribute(RequestParameter.ORDER, orderOptional.get());
            }
            page = PagePath.ORDER;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding order", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
