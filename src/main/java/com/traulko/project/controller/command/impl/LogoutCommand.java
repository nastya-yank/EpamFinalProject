package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code LogoutCommand} class represents logout command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class LogoutCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(RequestParameter.USER);
            session.removeAttribute(RequestParameter.USER_ID);
            session.setAttribute(RequestParameter.ROLE, User.Role.GUEST.toString());
        }
        return PagePath.LOGIN;
    }
}
