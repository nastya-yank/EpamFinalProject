package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code LoginPageCommand} class represents browse login page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class LoginPageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.LOGIN;
    }
}
