package com.traulko.project.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CustomCommand} interface represents command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface CustomCommand {

    /**
     * Execute command.
     *
     * @param request the request
     * @return the String containing page path
     */
    String execute(HttpServletRequest request);
}
