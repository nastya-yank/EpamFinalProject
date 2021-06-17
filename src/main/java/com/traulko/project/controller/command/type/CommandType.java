package com.traulko.project.controller.command.type;

import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.*;
import com.traulko.project.controller.command.impl.page.*;

/**
 * The {@code CommandType} enum represents command type.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    REGISTER_COMMAND(new RegisterCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    ACCOUNT_ACCESS_COMMAND(new AccountAccessCommand()),
    FIND_USERS_COMMAND(new FindUsersCommand()),
    DELETE_USER_COMMAND(new DeleteUserCommand()),
    BLOCK_USER_COMMAND(new BlockUserCommand()),
    UNBLOCK_USER_COMMAND(new UnblockUserCommand()),
    ADD_PRODUCT_COMMAND(new AddProductCommand()),
    FORGOT_PASSWORD_COMMAND(new ForgotPasswordCommand()),
    CHANGE_PASSWORD_COMMAND(new ChangePasswordCommand()),
    ADD_PRODUCT_TO_BASKET_COMMAND(new AddProductToBasketCommand()),
    DELETE_PRODUCT_FROM_BASKET_COMMAND(new DeleteProductFromBasketCommand()),
    FILL_UP_BALANCE_COMMAND(new FillUpBalanceCommand()),
    FIND_PRODUCTS_COMMAND(new FindProductsCommand()),
    CREATE_ORDER_COMMAND(new CreateOrderCommand()),
    PRODUCE_ORDER_COMMAND(new ProduceOrderCommand()),
    REJECT_ORDER_COMMAND(new RejectOrderCommand()),
    EDIT_PRODUCT_COMMAND(new EditProductCommand()),
    UNDO_ORDER_COMMAND(new UndoOrderCommand()),
    FIND_ORDERS_COMMAND(new FindOrdersCommand()),

    SWITCH_LANGUAGE_COMMAND(new SwitchLanguageCommand()),

    EMPTY_COMMAND(new EmptyCommand()),

    MAIN_PAGE(new MainPageCommand()),
    ADMIN_USERS_PAGE(new AdminUsersPageCommand()),
    ADMIN_ORDERS_PAGE(new AdminOrdersPageCommand()),
    PRODUCT_PAGE(new ProductPageCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    PERSONAL_ACCOUNT_PAGE(new PersonalAccountPageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    CATALOG_PAGE(new CatalogPageCommand()),
    FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    CHANGE_PASSWORD_PAGE(new ChangePasswordPageCommand()),
    BASKET_PAGE(new BasketPageCommand()),
    FILL_UP_BALANCE_PAGE(new FillUpBalancePageCommand()),
    ORDER_PAGE(new OrderPageCommand()),
    PRODUCT_ACTIONS_PAGE(new ProductActionsPageCommand()),
    ORDER_HISTORY_PAGE(new OrderHistoryPageCommand()),
    ADD_PRODUCT_PAGE(new ProductActionsPageCommand());

    private final CustomCommand command;

    CommandType(CustomCommand command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public CustomCommand getCommand() {
        return command;
    }
}
