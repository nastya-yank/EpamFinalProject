<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<div>
    <footer>
        <div class="container text-center">
            <p style="font-weight: bold"><fmt:message key="footer.payment_message"/> <fmt:message
                    key="footer.agreement_message"/></p>
            <p style="font-weight: bold"><fmt:message key="footer.copyright_message"/></p>
        </div>
    </footer>
</div>
