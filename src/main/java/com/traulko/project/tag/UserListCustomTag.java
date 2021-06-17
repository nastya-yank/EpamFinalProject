package com.traulko.project.tag;

import com.traulko.project.controller.RequestParameter;
import com.traulko.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code UserListCustomTag} class represents user list custom tag for admin.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserListCustomTag extends TagSupport {
    private static final String LOCAL = "property/local";
    private static final String SHOW_HISTORY = "admin_users_page.show_history";
    private static final String BLOCK = "admin_users_page.block";
    private static final String UNBLOCK = "admin_users_page.unblock";
    private static final String DELETE = "admin_users_page.delete";
    private static final String DELETE_COMMAND = "delete_user_command";
    private static final String BLOCK_COMMAND = "block_user_command";
    private static final String UNBLOCK_COMMAND = "unblock_user_command";
    private static final String ORDER_HISTORY_PAGE = "order_history_page";
    private static final Logger LOGGER = LogManager.getLogger(UserListCustomTag.class);

    @Override
    public int doStartTag() throws JspException {
        HttpSession httpSession = pageContext.getSession();
        Locale locale = new Locale((String) httpSession.getAttribute(RequestParameter.CURRENT_LOCALE));
        ResourceBundle bundle = ResourceBundle.getBundle(LOCAL, locale);
        ServletRequest servletRequest = pageContext.getRequest();
        List<User> userList = (List<User>) servletRequest.getAttribute(RequestParameter.USERS);
        int index = 0;
        while (index < userList.size()) {
            User user = userList.get(index);
            String commandChoice = user.getStatus() == User.Status.NOT_CONFIRMED ? DELETE_COMMAND :
                    user.getStatus() == User.Status.ENABLE ? BLOCK_COMMAND : UNBLOCK_COMMAND;
            String actionChoice = user.getStatus() == User.Status.NOT_CONFIRMED ? DELETE :
                    user.getStatus() == User.Status.ENABLE ? BLOCK : UNBLOCK;
            try {
                pageContext.getOut().write("<tr><td style=\"vertical-align: middle\">" + user.getName() + "</td>" +
                        "<td style=\"vertical-align: middle\">" + user.getSurname() + "</td>" +
                        "<td style=\"vertical-align: middle\">" + user.getPatronymic() + "</td>" +
                        "<td style=\"vertical-align: middle\">" + user.getRole() + "</td>" +
                        "<td style=\"vertical-align: middle\">" + user.getStatus() + "</td>" +
                        "<td style=\"vertical-align: middle\">" + user.getEmail() + "</td>" +
                        "<td style=\"vertical-align: middle\"><form method=\"post\" action=\"controller\">\n" +
                        "<input type=\"hidden\" name =\"commandName\" value=\"" + commandChoice + "\">\n" +
                        "<button class=\"btn btn-secondary nav-link\" name=\"email\"\n" +
                        "value=\"" + user.getEmail() + "\">" + bundle.getString(actionChoice) + "\n" +
                        "</button>\n" +
                        "</form></td>" +
                        "<td style=\"vertical-align: middle\"><form method=\"post\" action=\"controller\">\n" +
                        "<input type=\"hidden\" name =\"commandName\" value=\"" + ORDER_HISTORY_PAGE + "\">\n" +
                        "<button class=\"btn btn-secondary nav-link\" name=\"userId\"\n" +
                        "value=\"" + user.getUserId() + "\">" + bundle.getString(SHOW_HISTORY) +
                        "</button>\n" +
                        "</form></td></tr>");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while write out");
            }
            index++;
        }
        return SKIP_BODY;
    }
}
