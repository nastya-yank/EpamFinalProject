<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="forgot_password.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<footer>
    <jsp:include page="header.jsp"/>
</footer>
<main>
    <div class="container">
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form method="post" action="controller" autocomplete="off">
                <div>
                    <label for="email"><fmt:message key="forgot_password.email"/></label>
                    <input id="email" class="form-control" required type="text" name="email"
                           oninvalid="this.setCustomValidity('<fmt:message key="forgot_password.email_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\.[A-Za-z0-9]+$"
                           title='<fmt:message key="forgot_password.email_validator"/>'><br/>
                </div>
                <div class="form-row text-center">
                    <div class="col-12">
                        <button type="submit" class="btn btn-secondary"
                                name="commandName" value="forgot_password_command">
                            <fmt:message key="forgot_password.send_code_button"/>
                        </button>
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
