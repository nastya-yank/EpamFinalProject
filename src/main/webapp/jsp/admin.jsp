<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="admin_page.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form method="post" action="controller">
                <button class="btn btn-secondary nav-link btn-block" name="commandName"
                        value="admin_users_page"><fmt:message key="admin_page.users"/></button>
            </form>
            <form method="post" action="controller">
                <button class="btn btn-secondary nav-link btn-block" name="commandName"
                        value="add_product_page"><fmt:message key="admin_page.add_product"/></button>
            </form>
            <form method="post" action="controller">
                <button class="btn btn-secondary nav-link btn-block" name="commandName"
                        value="admin_orders_page"><fmt:message key="admin_page.orders"/></button>
            </form>
        </div>
    </div>

</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
