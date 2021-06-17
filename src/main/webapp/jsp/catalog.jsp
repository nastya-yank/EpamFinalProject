<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="catalog_page.title"/></title>

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
            <c:if test="${products.size() != 0}">
                <div class="col-6">
                    <form method="post" action="controller">
                        <input type="text" class="form-control" name="searchProductsQuery"
                               placeholder="<fmt:message key="catalog_page.product_search"/>">
                        <div class="form-row text-center">
                            <div class="col-12">
                                <button class="btn btn-secondary button-margin" name="commandName"
                                        value="find_products_command"><fmt:message key="catalog_page.search_button"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <c:forEach var="product" items="${products}">
                        <div class="col-sm-4">
                            <div class="card text-white bg-secondary card-margin-bottom">
                                <div>
                                    <img style="object-fit: cover; height: 200px" class="card-img-top"
                                         src="image/${product.getImage().getName()}.jpg"
                                         alt="Card image cap">
                                </div>
                                <div class="card-body">
                                    <h5 style="text-align: center" class="card-title">${product.getTitle()}</h5>
                                    <h5 style="text-align: center" class="card-title">${product.getPrice()} <fmt:message
                                            key="catalog_page.currency"/></h5>
                                    <div class="form-row text-center">
                                        <div class="col-12">
                                            <form method="post" action="controller">
                                                <input type="hidden" name="commandName" value="product_page">
                                                <button class="btn btn-light nav-link btn-block" name="productId"
                                                        value="${product.getProductId()}"><fmt:message
                                                        key="catalog_page.product_button"/>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${products.size() == 0}}">
                <h3><fmt:message key="catalog_page.search_no_result"/></h3>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
