package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderItemService;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderItemServiceImpl;
import com.traulko.project.service.impl.OrderServiceImpl;
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
