<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple ATM</title>
</head>
<body>
    <h3>Balance</h3>

    <c:out value="${currentDate}"/><br/>
    <c:out value="${number}"/><br/>
    <c:out value="${amount}$"/><br/>

    <a href="<c:url value='/operations'/>">Back</a><br/>

    <form action=" <c:url value='/j_spring_security_logout'/>" method="post">
        <input type="submit" value="Exit" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>
