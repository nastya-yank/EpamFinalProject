<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="change_password.title"/></title>

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
                    <label for="password"><fmt:message key="change_password.password"/></label>
                    <input id="password" class="form-control" required type="text" name="password" maxlength="16"
                           minlength="6"
                           oninvalid="this.setCustomValidity('<fmt:message key="change_password.password_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$"
                           title='<fmt:message key="change_password.password_validator"/>'><br/>
                </div>
                <div>
                    <label for="password_repeat"><fmt:message key="change_password.password_repeat"/></label>
                    <input id="password_repeat" class="form-control" required type="text" name="password_repeat"
                           maxlength="16"
                           minlength="6"
                           oninvalid="this.setCustomValidity('<fmt:message key="change_password.password_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$"
                           title='<fmt:message key="change_password.password_validator"/>'><br/>
                </div>
                <div class="form-row text-center">
                    <div class="col-12">
                        <button type="submit" class="btn btn-secondary"
                                name="commandName" value="change_password_command">
                            <fmt:message key="change_password.change_password_button"/>
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