<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="admin_orders_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <c:if test="${orders.size() != 0}">
                <div class="col-6">
                    <form method="post" action="controller">
                        <input type="text" class="form-control" name="searchOrdersQuery"
                               placeholder="<fmt:message key="admin_orders_page.order_search"/>">
                        <div class="form-row text-center">
                            <div class="col-12">
                                <button class="btn btn-secondary button-margin" name="commandName"
                                        value="find_orders_command"><fmt:message key="admin_orders_page.search"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <h1><fmt:message key="admin_orders_page.orders"/></h1>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th><fmt:message key="admin_orders_page.order_id"/></th>
                        <th><fmt:message key="admin_orders_page.creation_date"/></th>
                        <th><fmt:message key="admin_orders_page.closing_date"/></th>
                        <th><fmt:message key="admin_orders_page.status"/></th>
                        <th><fmt:message key="admin_orders_page.action"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <th>${order.getOrderId()}</th>
                            <th>${order.getCreationDate()}</th>
                            <th>${order.getClosingDate()}</th>
                            <th>${order.getStatus()}</th>
                            <th>
                                <form method="post" action="controller" autocomplete="off">
                                    <input type="hidden" name="commandName" value="order_page">
                                    <button class="btn btn-secondary" name="orderId"
                                            value="${order.getOrderId()}"><fmt:message
                                            key="admin_orders_page.more_info"/>
                                    </button>
                                </form>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${orders.size() == 0}">
                <h3><fmt:message key="admin_orders_page.search_no_result"/></h3>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
