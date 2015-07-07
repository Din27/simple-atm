<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple ATM</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        #container {
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
<body>
    <div id="container">
    <h3>Withdrawal</h3>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action=" <c:url value='/withdraw'/>" method="post">
            <input type="text" name="withdrawalAmount" autocomplete="off" /><br/>
            <input type="submit" value="Withdraw" /><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <a href="<c:url value='/operations'/>">Back</a>

        <form action=" <c:url value='/j_spring_security_logout'/>" method="post">
            <input type="submit" value="Exit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>
</html>
