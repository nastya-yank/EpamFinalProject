<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="basket_page.title"/></title>

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
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <div class="row">
                <c:if test="${baskets.size() == 0}">
                    <h3 style="text-align: center"><fmt:message key="basket_page.empty"/></h3>
                </c:if>
                <c:if test="${baskets.size() != 0}">
                    <c:forEach var="userBasketProduct" items="${baskets}">
                        <div class="col-sm-4">
                            <div class="card text-white bg-secondary card-margin-bottom">
                                <div>
                                    <img style="object-fit: cover; height: 200px" class="card-img-top"
                                         src="image/${userBasketProduct.getProduct().getImage().getName()}.jpg"
                                         alt="Card image cap">
                                </div>
                                <div class="card-body text-center shadow">
                                    <h5 class="card-title">${userBasketProduct.getProduct().getTitle()}</h5>
                                    <h3 style="text-align: center" class="lead">${product.getPrice()}</h3>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="commandName" value="product_page">
                                        <button class="btn btn-light btn-block nav-link" name="productId"
                                                value="${userBasketProduct.getProduct().getProductId().toString()}">
                                            <fmt:message
                                                    key="basket_page.product_page_button"/>
                                        </button>
                                    </form>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="commandName"
                                               value="delete_product_from_basket_command">
                                        <button class="btn btn-block btn-danger nav-link" name="productId"
                                                value="${userBasketProduct.getProduct().getProductId()}">
                                            <fmt:message
                                                    key="basket_page.remove_product_button"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <c:if test="${baskets.size() != 0}">
                <h4><fmt:message key="basket_page.total_price"/> ${totalPrice}</h4>
                <h4><fmt:message key="basket_page.balance"/> ${balance} <fmt:message
                        key="basket_page.currency"/></h4>
                <form method="post" action="controller">
                    <button class="btn btn-secondary nav-link" name="commandName"
                            value="fill_up_balance_page"><fmt:message
                            key="basket_page.fill_up_balance"/></button>
                </form>
                <c:if test="${totalPrice <= balance}">
                    <form method="post" action="controller">
                        <button class="btn btn-secondary" name="commandName"
                                value="create_order_command"><fmt:message key="basket_page.checkout"/></button>
                    </form>
                </c:if>
                <c:if test="${totalPrice > balance}">
                    <h4><fmt:message key="basket_page.low_balance"/></h4>
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
