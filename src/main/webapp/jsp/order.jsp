<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="order_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <h1 class="display-3"><fmt:message key="order_page.order_id"/> ${order.getOrderId()}</h1>
            <h3 class="lead"><fmt:message key="order_page.creation_date"/> ${order.getCreationDate()}</h3>
            <h3 class="lead"><fmt:message key="order_page.closing_date"/> ${order.getClosingDate()}</h3>
            <h3 class="lead"><fmt:message key="order_page.status"/> ${order.getStatus()}</h3>
            <div class="row">
                <c:forEach var="orderItem" items="${orderItems}">
                    <div class="col-sm-4">
                        <div class="card text-white bg-secondary card-margin-bottom">
                            <div>
                                <img style="object-fit: cover; height: 200px" class="card-img-top"
                                     src="image/${orderItem.getProduct().getImage().getName()}.jpg"
                                     alt="Card image cap">
                            </div>
                            <div class="card-body">
                                <h5 style="text-align: center"
                                    class="card-title">${orderItem.getProduct().getTitle()}</h5>
                                <h5 style="text-align: center" class="card-title">${orderItem.getProduct().getPrice()}
                                    <fmt:message
                                            key="order_page.currency"/></h5>
                                <div class="form-row text-center">
                                    <div class="col-12">
                                        <form method="post" action="controller">
                                            <input type="hidden" name="commandName" value="product_page">
                                            <button class="btn btn-light nav-link btn-block" name="productId"
                                                    value="${orderItem.getProduct().getProductId()}"><fmt:message
                                                    key="order_page.go_to_product"/>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${order.getStatus().toString().equals(\"UNDER_CONSIDERATION\")}">
                <c:if test="${user.role.toString().equals(\"ADMIN\")}">
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="produce_order_command">
                        <button class="btn btn-success nav-link btn-block" name="orderId"
                                value="${order.getOrderId()}"><fmt:message
                                key="order_page.produce_button"/>
                        </button>
                    </form>
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="reject_order_command">
                        <button class="btn btn-danger nav-link btn-block" name="orderId"
                                value="${order.getOrderId()}"><fmt:message
                                key="order_page.reject_button"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${user.role.toString().equals(\"USER\")}">
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="undo_order_command">
                        <button class="btn btn-danger nav-link btn-block" name="orderId"
                                value="${order.getOrderId()}"><fmt:message
                                key="order_page.undo_button"/>
                        </button>
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>