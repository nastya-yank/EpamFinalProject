package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code RegistrationPageCommand} class represents browse registration page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class RegistrationPageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.REGISTRATION;
    }
}
