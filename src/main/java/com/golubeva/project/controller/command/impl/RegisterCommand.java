package com.golubeva.project.controller.command.impl;

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
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code RegisterCommand} class represents register command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class RegisterCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_REPEAT);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String patronymic = request.getParameter(RequestParameter.PATRONYMIC);
        Map<String, String> registrationParameters = new HashMap<>();
        registrationParameters.put(RequestParameter.EMAIL, email);
        registrationParameters.put(RequestParameter.NAME, name);
        registrationParameters.put(RequestParameter.SURNAME, surname);
        registrationParameters.put(RequestParameter.PATRONYMIC, patronymic);
        registrationParameters.put(RequestParameter.PASSWORD, password);
        registrationParameters.put(RequestParameter.PASSWORD_REPEAT, passwordRepeat);
        try {
            if (userService.addUser(registrationParameters)) {
                User user = userService.findUserByEmail(email).get();
                userService.sendLetter(user, request.getRequestURL().toString());
                request.setAttribute(RequestParameter.USER_CONFIRM_REGISTRATION_LETTER, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.REGISTRATION_PARAMETERS, registrationParameters);
                page = PagePath.REGISTRATION;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while register user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
