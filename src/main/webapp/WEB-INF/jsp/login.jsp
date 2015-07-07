<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>

<body onload='document.loginForm.j_pin.focus();'>
    <div id="container">

        <h3>PIN for <c:out value="${number}"/>:</h3>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form name='loginForm'
              action="<c:url value='/j_spring_security_check' />" method='POST'
              autocomplete="off">

            <!-- fake fields are a workaround for chrome autofill getting the wrong fields (chrome ignores autocomplete="off") -->
            <input style="display:none" type="text" name="fakeusernameremembered"/>
            <input style="display:none" type="password" name="fakepasswordremembered"/>

            <input style="display:none" type='text' name='j_number' value='${number}' autocomplete="off"/>

            <input type='password' name='j_pin' autocomplete="off"/>
            <input name="submit" type="submit" value="submit" />

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
    </div>
</body>

<jsp:include page="_foot.jsp"/>
