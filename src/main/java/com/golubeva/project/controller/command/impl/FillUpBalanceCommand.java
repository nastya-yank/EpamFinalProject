package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code FillUpBalanceCommand} class represents fill up balance command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FillUpBalanceCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FillUpBalanceCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        String moneyAmount = request.getParameter(RequestParameter.MONEY_AMOUNT);
        try {
            if (userService.fillUpBalance(userId, moneyAmount)) {
                request.setAttribute(RequestParameter.FILL_UP_BALANCE_SUCCESS, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_MONEY_AMOUNT, true);
                page = PagePath.FILL_UP_BALANCE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while filling up balance", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
