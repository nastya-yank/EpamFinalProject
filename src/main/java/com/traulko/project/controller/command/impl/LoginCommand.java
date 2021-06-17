package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The {@code LoginCommand} class represents login command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class LoginCommand implements CustomCommand {
    private static final UserService userService = new UserServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.isUserExists(email, password)) {
                Optional<User> optionalUser = userService.findUserByEmail(email);
                User user = optionalUser.get();
                switch (user.getStatus()) {
                    case ENABLE -> {
                        request.getSession().setAttribute(RequestParameter.USER, user);
                        request.getSession().setAttribute(RequestParameter.USER_ID, user.getUserId().toString());
                        request.getSession().setAttribute(RequestParameter.ROLE, user.getRole().toString());
                        page = PagePath.MAIN;
                    }
                    case BLOCKED -> {
                        request.setAttribute(RequestParameter.USER_LOGIN_BLOCKED, true);
                        page = PagePath.MESSAGE;
                    }
                    case NOT_CONFIRMED -> {
                        request.setAttribute(RequestParameter.USER_CONFIRM_REGISTRATION_LETTER, true);
                        page = PagePath.MESSAGE;
                    }
                    default -> page = PagePath.MAIN;
                }
            } else {
                request.setAttribute(RequestParameter.REGISTRATION_PARAMETERS, true);
                page = PagePath.LOGIN;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while login user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
