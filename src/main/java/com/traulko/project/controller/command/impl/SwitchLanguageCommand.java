package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.RequestAttributeHandler;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The {@code SwitchLanguageCommand} class represents switch language command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class SwitchLanguageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.NEW_LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.CURRENT_LOCALE, locale);
        RequestAttributeHandler requestAttributeHandler =
                (RequestAttributeHandler) session.getAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER);
        Map<String, Object> attributes = requestAttributeHandler.getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return (String) session.getAttribute(RequestParameter.CURRENT_PAGE);
    }
}
