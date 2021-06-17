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
import javax.servlet.http.HttpSession;

/**
 * The {@code ChangePasswordCommand} class represents change password command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ChangePasswordCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String email = request.getParameter(RequestParameter.EMAIL) != null ?
                request.getParameter(RequestParameter.EMAIL) :
                ((User) session.getAttribute(RequestParameter.USER)).getEmail();
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_REPEAT);
        try {
            if (userService.changePassword(email, password, passwordRepeat)) {
                request.setAttribute(RequestParameter.USER_SUCCESS_CHANGE_PASSWORD, true);
                session.removeAttribute(RequestParameter.EMAIL);
                if (session.getAttribute(RequestParameter.USER) != null) {
                    session.removeAttribute(RequestParameter.USER);
                }
                page = PagePath.LOGIN;
            } else {
                request.setAttribute(RequestParameter.USER_ERROR_CHANGE_PASSWORD, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while changing password", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
