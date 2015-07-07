<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>
<body onload='document.creditCardNumberForm.number.focus();'>
    <div id="container">

        <h3>Credit Card Number:</h3>

        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <form name='creditCardNumberForm'
              action="<c:url value='enter_card' />" method='POST'
              autocomplete="off">

            <input type='text' name='number' value=''>
            <input name="submit" type="submit" value="submit" />

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>
    </div>
</body>

<jsp:include page="_foot.jsp"/>
