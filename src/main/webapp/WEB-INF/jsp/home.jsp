<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>
<body onload='document.creditCardNumberForm.number.focus();'>
    <div id="container">

        <h3>Credit Card Number:</h3>

        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <form id="creditCardNumberForm"
              name='creditCardNumberForm'
              action="<c:url value='enter_card' />"
              method='POST'
              autocomplete="off">

            <c:set var="numberInputId" value="number-input"/>
            <input id='${numberInputId}' type='text' name='number' value='' readonly required>

            <jsp:include page="_keypad.jsp"/>
            <script>$(document).ready(function() { startKeypad('#${numberInputId}', 19, true); });</script>

            <input name="submit" type="submit" value="submit" />

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>

        <script>
            $(document).ready(function() {
                $('#creditCardNumberForm').submit(function() {
                    if ($.trim($("#${numberInputId}").val()) === "") {
                        return false;
                    }
                });
            });
        </script>
    </div>
</body>

<jsp:include page="_foot.jsp"/>
