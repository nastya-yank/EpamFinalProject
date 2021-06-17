<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="registration_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form method="post" action="controller" autocomplete="off">
                <div>
                    <label for="email"><fmt:message key="registration_page.email"/></label>
                    <input id="email" class="form-control" required type="text" name="email"
                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.email_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           value="${registrationParameters.get("email")}"
                           pattern="^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\.[A-Za-z0-9]+$"
                           title='<fmt:message key="registration_page.email_validator"/>'><br/>
                </div>
                <div>
                    <label for="password"><fmt:message key="registration_page.password"/></label>
                    <input id="password" class="form-control" required type="text" name="password" maxlength="16"
                           minlength="6"
                           value="${registrationParameters.get("password")}"
                           oninvalid="this.setCustomValidity('<fmt:message
                                   key="registration_page.password_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$"
                           title='<fmt:message key="registration_page.password_validator"/>'><br/>
                </div>
                <div>
                    <label for="password_repeat"><fmt:message key="registration_page.repeat_password"/></label>
                    <input id="password_repeat" class="form-control" required type="text" name="password_repeat"
                           maxlength="16"
                           minlength="6"
                           value="${registrationParameters.get("password_repeat")}"
                           oninvalid="this.setCustomValidity('<fmt:message
                                   key="registration_page.password_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$"
                           title='<fmt:message key="registration_page.password_validator"/>'><br/>
                </div>
                <div>
                    <label for="name"><fmt:message key="registration_page.name"/></label>
                    <input id="name" class="form-control" required type="text" name="name" maxlength="25"
                           minlength="2"
                           value="${registrationParameters.get("name")}"
                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.name_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^\p{L}{2,25}$"
                           title='<fmt:message key="registration_page.name_validator"/>'><br/>
                </div>
                <div>
                    <label for="surname"><fmt:message key="registration_page.surname"/></label>
                    <input id="surname" class="form-control" required type="text" name="surname" maxlength="25"
                           minlength="2"
                           value="${registrationParameters.get("surname")}"
                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.name_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^\p{L}{2,25}$"
                           title='<fmt:message key="registration_page.name_validator"/>'><br/>
                </div>
                <div>
                    <label for="patronymic"><fmt:message key="registration_page.patronymic"/></label>
                    <input id="patronymic" class="form-control" required type="text" name="patronymic" maxlength="25"
                           minlength="2"
                           value="${registrationParameters.get("patronymic")}"
                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.name_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^\p{L}{2,25}$"
                           title='<fmt:message key="registration_page.name_validator"/>'><br/>
                </div>
                <c:if test="${registrationParameters != null && registrationParameters.get(\"email\").equals(\"\")}">
                    <div style="color: red">
                        <p><fmt:message key="registration_page.incorrect_email"/></p>
                    </div>
                </c:if>
                <c:if test="${registrationParameters != null && registrationParameters.get(\"password\").equals(\"\")}">
                    <div style="color: red">
                        <p><fmt:message key="registration_page.incorrect_password"/></p>
                    </div>
                </c:if>
                <div class="form-row text-center">
                    <div class="col-12">
                        <button type="submit" class="btn btn-secondary"
                                name="commandName" value="register_command">
                            <fmt:message key="registration_page.register_button"/>
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