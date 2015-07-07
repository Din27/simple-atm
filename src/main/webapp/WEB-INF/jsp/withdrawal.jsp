<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple ATM</title>
</head>
<body>
    <h3>Withdrawal</h3>


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
</body>
</html>
