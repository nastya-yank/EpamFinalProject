package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

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
