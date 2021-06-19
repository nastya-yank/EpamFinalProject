package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * The {@code AccountAccessCommand} class represents account access command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class AccountAccessCommand implements CustomCommand {
    private static final UserService userService = new UserServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String code = request.getParameter(RequestParameter.ACCESS_CODE);
        try {
            List<User> userList = userService.findAllUsers();
            Optional<User> optionalUser = userService.findUserByAccessCode(code, userList);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getStatus() == User.Status.NOT_CONFIRMED) {
                    userService.activateUser(user.getEmail());
                    request.setAttribute(RequestParameter.USER_SUCCESS_CONFIRM_REGISTRATION_LETTER, true);
                    page = PagePath.MESSAGE;
                } else {
                    request.getSession().setAttribute(RequestParameter.EMAIL, user.getEmail());
                    page = PagePath.CHANGE_PASSWORD;
                }
            } else {
                request.setAttribute(RequestParameter.USER_CODE_NOT_FOUND, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while activating account", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
