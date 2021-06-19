package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.User;
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
