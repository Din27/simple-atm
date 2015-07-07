<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple ATM</title>
    <style>
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
        <h3>Balance</h3>

        <c:out value="${currentDate}"/><br/>
        <c:out value="${number}"/><br/>
        -------------<br/>
        <c:out value="=${amount}$"/><br/>

        <a href="<c:url value='/operations'/>">Back</a><br/>

        <form action=" <c:url value='/j_spring_security_logout'/>" method="post">
            <input type="submit" value="Exit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>
</html>
