package com.traulko.project.dao;

/**
 * The {@code ColumnName} class represents column name.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ColumnName {

    /**
     * Constants for user table
     */
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ROLE = "user_role";
    public static final String USER_STATUS = "user_status";
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String USER_PATRONYMIC = "user_patronymic";
    public static final String USER_BALANCE = "user_balance";

    /**
     * Constants for product table
     */
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_TITLE = "product_title";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_DESCRIPTION = "product_description";

    /**
     * Constants for image table
     */
    public static final String IMAGE_ID = "image_id";
    public static final String IMAGE_NAME = "image_name";

    /**
     * Constants for basket table
     */
    public static final String BASKET_ID = "basket_id";

    /**
     * Constants for order table
     */
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_CREATION_DATE = "order_creation_date";
    public static final String ORDER_CLOSING_DATE = "order_closing_date";
    public static final String ORDER_STATUS = "order_status";

    /**
     * Constants for order item table
     */
    public static final String ORDER_ITEM_ID = "order_item_id";

    private ColumnName() {
    }
}
