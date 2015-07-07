<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>

<body>
    <div id="container">
        <h3>Operation Report</h3>

        <c:out value="${currentDate}"/><br/>
        <c:out value="${number}"/><br/>
        <br/>
        <c:out value="-${withdrawalAmount}$"/><br/>
        -------------<br/>
        <c:out value="=${balanceAmount}$"/><br/>

        <a href="<c:url value='/operations'/>">Back</a><br/>

        <form action=" <c:url value='/j_spring_security_logout'/>" method="post">
            <input type="submit" value="Exit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>

<jsp:include page="_foot.jsp"/>