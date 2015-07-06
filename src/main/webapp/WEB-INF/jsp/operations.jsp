<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple ATM</title>
</head>
<body>
    <h3>Operations</h3>

    <c:url var="logoutUrl" value="/j_spring_security_logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Exit" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</body>
</html>
