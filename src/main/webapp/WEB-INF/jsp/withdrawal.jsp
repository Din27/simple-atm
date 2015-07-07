<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>

<body onload='document.withdrawalForm.withdrawalAmount.focus();'>
    <div id="container">
    <h3>Withdrawal</h3>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form id="withdrawalForm"
              name='withdrawalForm'
              action=" <c:url value='/withdraw'/>"
              autocomplete="off"
              method="POST">

            <c:set var="withdrawalAmountInputId" value="pin-input"/>
            <input id="${withdrawalAmountInputId}" type="text" name="withdrawalAmount" autocomplete="off" readonly required/><br/>

            <jsp:include page="_keypad.jsp"/>
            <script>$(document).ready(function() { startKeypad('#${withdrawalAmountInputId}', 13, false, "Delete"); });</script>

            <input type="submit" value="Withdraw" /><br/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <a href="<c:url value='/operations'/>">Back</a>

        <form action=" <c:url value='/j_spring_security_logout'/>"
              method="post">
            <input type="submit" value="Exit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <script>
            $(document).ready(function() {
                $('#withdrawalForm').submit(function() {
                    if ($.trim($("#${withdrawalAmountInputId}").val()) === "") {
                        return false;
                    }
                });
            });
        </script>
    </div>
</body>

<jsp:include page="_foot.jsp"/>
