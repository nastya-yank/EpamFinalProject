package com.golubeva.project.controller.command.impl.page;

import com.golubeva.project.controller.PagePath;
import com.golubeva.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code FillUpBalancePageCommand} class represents browse fill up balance page command.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FillUpBalancePageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.FILL_UP_BALANCE;
    }
}
