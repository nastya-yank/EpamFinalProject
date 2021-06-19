package com.golubeva.project.controller.command.impl;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.service.impl.UserServiceImpl;
import com.golubeva.project.util.XssSecurity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code FindUsersCommand} class represents find users command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FindUsersCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FindUsersCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String searchQuery = request.getParameter(RequestParameter.SEARCH_USERS_QUERY);
        try {
            String searchQuerySecured = XssSecurity.secure(searchQuery);
            List<User> userList = userService.findUsersBySearchQuery(searchQuerySecured);
            request.setAttribute(RequestParameter.USERS, userList);
            page = PagePath.ADMIN_USERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
