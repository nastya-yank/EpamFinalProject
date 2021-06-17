<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="personal_account.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-6 mx-auto my-lg-4 p-3 bg-light">
            <h2 class="display-3">${user.getName()}</h2>
            <h2 class="display-3">${user.getSurname()}</h2>
            <h2 class="display-3">${user.getPatronymic()}</h2>
            <h3 class="lead">${user.getEmail()}</h3>
            <form method="post" action="controller" autocomplete="off">
                <button type="submit" class="btn btn-secondary btn-block"
                        name="commandName" value="change_password_page">
                    <fmt:message key="personal_account.change_password_button"/>
                </button>
            </form>
            <c:if test="${user.getRole().toString().equals(\"USER\")}">
                <form method="post" action="controller" autocomplete="off">
                    <input type="hidden" name="commandName" value="order_history_page">
                    <button type="submit" class="btn btn-secondary btn-block"
                            name="userId" value="${user.getUserId()}">
                        <fmt:message key="personal_account.history"/>
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
