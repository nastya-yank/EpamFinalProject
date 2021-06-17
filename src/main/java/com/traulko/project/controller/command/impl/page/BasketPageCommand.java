package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserBasketProductService;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserBasketProductServiceImpl;
import com.traulko.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code BasketPageCommand} class represents browse basket page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class BasketPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(BasketPageCommand.class);
    private static final UserBasketProductService basketService = new UserBasketProductServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        try {
            List<UserBasketProduct> userBasketProductList = basketService.findUserBasketProductsByUserId(userId);
            double totalPrice = basketService.calculateTotalPrice(userBasketProductList);
            request.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
            request.setAttribute(RequestParameter.BASKETS, userBasketProductList);
            Optional<User> optionalUser = userService.findUserById(userId);
            if (optionalUser.isPresent()) {
                double userBalance = optionalUser.get().getBalance();
                request.setAttribute(RequestParameter.BALANCE, userBalance);
            } else {
                request.setAttribute(RequestParameter.BALANCE_LOADING_ERROR, true);
            }
            page = PagePath.BASKET;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding user basket", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
