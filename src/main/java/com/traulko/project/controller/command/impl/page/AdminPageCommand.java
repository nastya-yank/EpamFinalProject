package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code AdminPageCommand} class represents browse admin page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class AdminPageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ADMIN;
    }
}
