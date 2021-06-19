package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code MainPageCommand} class represents browse mail page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class MainPageCommand implements CustomCommand {
    private static final String ENG_LOCALE = "en";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(RequestParameter.CURRENT_LOCALE, ENG_LOCALE);
            session.setAttribute(RequestParameter.ROLE, User.Role.GUEST.toString());
        }
        return PagePath.MAIN;
    }
}
