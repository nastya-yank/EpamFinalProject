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
 * The {@code ForgotPasswordCommand} class represents forgot password command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ForgotPasswordCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        try {
            Optional<User> optionalUser = userService.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userService.sendLetter(user, request.getRequestURL().toString());
                request.setAttribute(RequestParameter.USER_SUCCESS_CHANGE_PASSWORD_LETTER, true);
            } else {
                request.setAttribute(RequestParameter.USER_EMAIL_IS_NOT_FOUND, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error forgot password", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
