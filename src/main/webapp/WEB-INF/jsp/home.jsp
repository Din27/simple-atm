<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 500px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.creditCardNumberForm.number.focus();'>

<div id="login-box">

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
</html>
