package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code ChangePasswordPageCommand} class represents browse change password page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ChangePasswordPageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.CHANGE_PASSWORD;
    }
}
