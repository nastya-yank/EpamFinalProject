package com.traulko.project.controller.command;

import com.traulko.project.controller.command.type.CommandType;

import java.util.EnumSet;
import java.util.Set;

/**
 * The {@code RoleAccess} enum represents role access.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public enum RoleAccess {

    /**
     * Guest role access.
     */
    GUEST(EnumSet.of(CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.LOGIN_COMMAND,
            CommandType.REGISTER_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.FORGOT_PASSWORD_COMMAND,
            CommandType.CATALOG_PAGE,
            CommandType.LOGIN_PAGE,
            CommandType.FORGOT_PASSWORD_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.PRODUCT_PAGE,
            CommandType.REGISTRATION_PAGE)),

    /**
     * User role access.
     */
    USER(EnumSet.of(CommandType.ACCOUNT_ACCESS_COMMAND,
            CommandType.ADD_PRODUCT_TO_BASKET_COMMAND,
            CommandType.CHANGE_PASSWORD_COMMAND,
            CommandType.CREATE_ORDER_COMMAND,
            CommandType.DELETE_PRODUCT_FROM_BASKET_COMMAND,
            CommandType.FILL_UP_BALANCE_COMMAND,
            CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.LOGOUT_COMMAND,
            CommandType.BASKET_PAGE,
            CommandType.CATALOG_PAGE,
            CommandType.CHANGE_PASSWORD_PAGE,
            CommandType.FILL_UP_BALANCE_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.ORDER_PAGE,
            CommandType.PERSONAL_ACCOUNT_PAGE,
            CommandType.UNDO_ORDER_COMMAND,
            CommandType.ORDER_HISTORY_PAGE,
            CommandType.PRODUCT_PAGE)),

    /**
     * Admin role access.
     */
    ADMIN(EnumSet.of(CommandType.ACCOUNT_ACCESS_COMMAND,
            CommandType.ADD_PRODUCT_COMMAND,
            CommandType.BLOCK_USER_COMMAND,
            CommandType.FIND_ORDERS_COMMAND,
            CommandType.CHANGE_PASSWORD_COMMAND,
            CommandType.DELETE_USER_COMMAND,
            CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.FIND_USERS_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.UNBLOCK_USER_COMMAND,
            CommandType.LOGOUT_COMMAND,
            CommandType.EDIT_PRODUCT_COMMAND,
            CommandType.PRODUCT_ACTIONS_PAGE,
            CommandType.ADD_PRODUCT_PAGE,
            CommandType.ADMIN_PAGE,
            CommandType.CATALOG_PAGE,
            CommandType.CHANGE_PASSWORD_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.ORDER_PAGE,
            CommandType.ADMIN_ORDERS_PAGE,
            CommandType.PRODUCE_ORDER_COMMAND,
            CommandType.REJECT_ORDER_COMMAND,
            CommandType.ADMIN_USERS_PAGE,
            CommandType.PERSONAL_ACCOUNT_PAGE,
            CommandType.ORDER_HISTORY_PAGE,
            CommandType.PRODUCT_PAGE));

    private Set<CommandType> accessCommands;

    RoleAccess(Set<CommandType> accessCommands) {
        this.accessCommands = accessCommands;
    }

    /**
     * Gets access commands.
     *
     * @return the commands
     */
    public Set<CommandType> getAccessCommands() {
        return this.accessCommands;
    }
}
