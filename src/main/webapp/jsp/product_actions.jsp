<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="product_actions.title"/></title>

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
            <c:if test="${product == null}">
            <form method="post" action="image" enctype="multipart/form-data" autocomplete="off">
                </c:if>
                <c:if test="${product != null}">
                <form method="post" action="controller" autocomplete="off">
                    <input type="hidden" name="productId" value="${product.getProductId()}">
                    </c:if>
                    <div>
                        <label for="title"><fmt:message key="product_actions.product_title"/></label>
                        <input id="title" class="form-control" required type="text" name="title"
                               maxlength="25" , minlength="2" value="${product.getTitle()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="product_actions.title_validator"/>')"
                               onchange="this.setCustomValidity('')"
                               pattern="^[^<>]{2,25}$"
                               title='<fmt:message key="product_actions.title_validator"/>'><br/>
                    </div>
                    <div>
                        <label for="price"><fmt:message key="product_actions.price"/></label>
                        <input id="price" class="form-control" required type="text" name="price"
                               maxlength="7" value="${product.getPrice()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="product_actions.price_validator"/>')"
                               onchange="this.setCustomValidity('')"
                               pattern="^[1-9]\d{0,4}(\.\d{0,2})?$"
                               title='<fmt:message key="product_actions.price_validator"/>'><br/>
                    </div>
                    <div>
                        <label for="description"><fmt:message key="product_actions.description"/></label>
                        <input id="description" class="form-control" required type="text" name="description"
                               maxlength="1000"
                               value="${product.getDescription()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="product_actions.description_validator"/>')"
                               onchange="this.setCustomValidity('')"
                               pattern="^[^<>]{1,1000}$"
                               title='<fmt:message key="product_actions.description_validator"/>'><br/>
                    </div>
                    <c:if test="${product == null}">
                        <div>
                            <label for="content"><fmt:message key="product_actions.image"/></label>
                            <input id="content" type="file" name="content" accept="image/jpeg" required">
                        </div>
                    </c:if>
                    <c:if test="${userDataIncorrect}">
                        <div style="color: red">
                            <p><fmt:message key="registration_page.incorrect_email"/></p>
                        </div>
                    </c:if>
                    <c:if test="${incorrectImageMessage}">
                        <div style="color: red">
                            <p><fmt:message key="registration_page.incorrect_image"/></p>
                        </div>
                    </c:if>
                    <div class="form-row text-center">
                        <div class="col-12">
                            <c:if test="${product == null}">
                                <button type="submit" class="btn btn-secondary"
                                        name="commandName" value="add_product_command">
                                    <fmt:message key="product_actions.add_button"/>
                                </button>
                            </c:if>
                            <c:if test="${product != null}">
                                <button type="submit" class="btn btn-secondary"
                                        name="commandName" value="edit_product_command">
                                    <fmt:message key="product_actions.edit_button"/>
                                </button>
                            </c:if>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
