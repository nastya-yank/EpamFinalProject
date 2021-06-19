package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.command.CustomCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * The {@code EmptyCommand} class represents empty command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class EmptyCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ERROR_404;
    }
}
