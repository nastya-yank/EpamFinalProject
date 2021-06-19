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

/**
 * The {@code UnblockUserCommand} class represents unblock user command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UnblockUserCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(UnblockUserCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        try {
            if (userService.unblockUser(email)) {
                request.setAttribute(RequestParameter.USER_UNBLOCK_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.USER_UNBLOCK_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while unblocking user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
