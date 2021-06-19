package com.golubeva.project.controller.filter;

import com.golubeva.project.controller.command.CommandProvider;
import com.golubeva.project.controller.command.CustomCommand;
import com.golubeva.project.controller.command.RoleAccess;
import com.golubeva.project.controller.command.impl.EmptyCommand;
import com.golubeva.project.controller.command.type.CommandType;
import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * The {@code RoleFilter} class represents role filter.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession();
        String commandName = httpRequest.getParameter(RequestParameter.COMMAND_NAME);
        Optional<CustomCommand> commandOptional = CommandProvider.defineCommand(commandName);
        if (commandOptional.isPresent()) {
            CustomCommand command = commandOptional.get();
            if (command.getClass() != EmptyCommand.class) {
                String roleName = (String) httpSession.getAttribute(RequestParameter.ROLE);
                User.Role role = roleName != null ? User.Role.valueOf(roleName.toUpperCase())
                        : User.Role.GUEST;
                Set<CommandType> commands = switch (role) {
                    case USER -> RoleAccess.USER.getAccessCommands();
                    case ADMIN -> RoleAccess.ADMIN.getAccessCommands();
                    case GUEST -> RoleAccess.GUEST.getAccessCommands();
                };
                if (commands != null && !commands.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                    LOGGER.log(Level.ERROR, "Role " + role + " has no access to command " + commandName);
                    request.setAttribute(RequestParameter.ACCESS_ERROR_MESSAGE, true);
                    request.getRequestDispatcher(PagePath.MESSAGE).forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
