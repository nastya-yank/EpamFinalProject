<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="error_404_page.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <section class="masthead">
        <div class="intro-body">
            <div class="container">
                <h5><fmt:message key="error_404_page.request"/> ${pageContext.errorData.requestURI}
                    <fmt:message key="error_404_page.failed"/></h5>
                <br/>
                <h5><fmt:message key="error_404_page.code"/> ${pageContext.errorData.statusCode}</h5>
                <br/>
            </div>
        </div>
    </section>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>

