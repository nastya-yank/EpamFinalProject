package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code AdminUsersPageCommand} class represents browse admin users page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class AdminUsersPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(AdminUsersPageCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<User> userList = userService.findAllUsers();
            request.setAttribute(RequestParameter.USERS, userList);
            page = PagePath.ADMIN_USERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding all users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
