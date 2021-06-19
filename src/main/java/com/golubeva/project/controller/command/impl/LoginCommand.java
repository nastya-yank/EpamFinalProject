package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.service.impl.UserServiceImpl;
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
