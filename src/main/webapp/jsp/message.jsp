<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="message_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <section class="masthead">
        <div class="intro-body">
            <div class="container">
                <c:if test="${userDeleteSuccessMessage}">
                    <h2><fmt:message key="message_page.user_delete_error"/></h2>
                </c:if>

                <c:if test="${userDeleteErrorMessage}">
                    <h2><fmt:message key="message_page.user_delete_success"/></h2>
                </c:if>

                <c:if test="${userBlockSuccessMessage}">
                    <h2><fmt:message key="message_page.user_block_success"/></h2>
                </c:if>

                <c:if test="${userBlockErrorMessage}">
                    <h2><fmt:message key="message_page.user_block_error"/></h2>
                </c:if>

                <c:if test="${userUnblockSuccessMessage}">
                    <h2><fmt:message key="message_page.user_unblock_success"/></h2>
                </c:if>

                <c:if test="${userUnblockErrorMessage}">
                    <h2><fmt:message key="message_page.user_unblock_error"/></h2>
                </c:if>

                <c:if test="${userConfirmRegistrationLetter}">
                    <h2><fmt:message key="message_page.user_confirm_letter"/></h2>
                </c:if>

                <c:if test="${userLoginBlocked}">
                    <h2><fmt:message key="message_page.user_login_blocked"/></h2>
                </c:if>

                <c:if test="${userSuccessConfirmRegistrationLetter}">
                    <h2><fmt:message key="message_page.user_success_confirm_registration_letter"/></h2>
                </c:if>

                <c:if test="${userCodeNotFound}">
                    <h2><fmt:message key="message_page.user_code_not_found"/></h2>
                </c:if>

                <c:if test="${userSuccessChangePassword}">
                    <h2><fmt:message key="message_page.user_success_change_password"/></h2>
                </c:if>

                <c:if test="${userErrorChangePassword}">
                    <h2><fmt:message key="message_page.user_error_change_password"/></h2>
                </c:if>

                <c:if test="${userSuccessChangePasswordLetter}">
                    <h2><fmt:message key="message_page.user_success_change_password_letter"/></h2>
                </c:if>

                <c:if test="${userEmailIsNotFound}">
                    <h2><fmt:message key="message_page.user_email_is_not_found"/></h2>
                </c:if>

                <c:if test="${addProductSuccess}">
                    <h2><fmt:message key="message_page.add_product_success"/></h2>
                </c:if>

                <c:if test="${addProductError}">
                    <h2><fmt:message key="message_page.add_product_error"/></h2>
                </c:if>

                <c:if test="${productFindError}">
                    <h2><fmt:message key="message_page.product_find_error"/></h2>
                </c:if>

                <c:if test="${addProductToBasketSuccess}">
                    <h2><fmt:message key="message_page.add_basket_success"/></h2>
                </c:if>

                <c:if test="${addProductToBasketError}">
                    <h2><fmt:message key="message_page.add_basket_error"/></h2>
                </c:if>

                <c:if test="${removeProductFromBasketSuccess}">
                    <h2><fmt:message key="message_page.remove_basket_success"/></h2>
                </c:if>

                <c:if test="${removeProductFromBaksetError}">
                    <h2><fmt:message key="message_page.remove_basket_error"/></h2>
                </c:if>

                <c:if test="${fillUpBalanceSuccess}">
                    <h2><fmt:message key="message_page.fill_up_success"/></h2>
                </c:if>

                <c:if test="${orderCreateSuccessMessage}">
                    <h2><fmt:message key="message_page.order_create_success"/></h2>
                </c:if>

                <c:if test="${orderCreateErrorMessage}">
                    <h2><fmt:message key="message_page.order_create_error"/></h2>
                </c:if>

                <c:if test="${accessErrorMessage}">
                    <h2><fmt:message key="message_page.access_error"/></h2>
                </c:if>

                <c:if test="${orderProduceSuccessMessage}">
                    <h2><fmt:message key="message_page.order_produce_success"/></h2>
                </c:if>

                <c:if test="${orderProduceErrorMessage}">
                    <h2><fmt:message key="message_page.order_produce_error"/></h2>
                </c:if>

                <c:if test="${orderRejectSuccessMessage}">
                    <h2><fmt:message key="message_page.order_reject_success"/></h2>
                </c:if>

                <c:if test="${orderRejectErrorMessage}">
                    <h2><fmt:message key="message_page.order_reject_error"/></h2>
                </c:if>

                <c:if test="${orderUndoSuccessMessage}">
                    <h2><fmt:message key="message_page.order_undo_success"/></h2>
                </c:if>

                <c:if test="${orderUndoErrorMessage}">
                    <h2><fmt:message key="message_page.order_undo_error"/></h2>
                </c:if>

                <c:if test="${updateProductSuccess}">
                    <h2><fmt:message key="message_page.update_product_success"/></h2>
                </c:if>

                <c:if test="${updateProductError}">
                    <h2><fmt:message key="message_page.update_product_error"/></h2>
                </c:if>

                <c:if test="${balanceLoadingError}">
                    <h2><fmt:message key="message_page.balance_loading_error"/></h2>
                </c:if>
            </div>
        </div>
    </section>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>