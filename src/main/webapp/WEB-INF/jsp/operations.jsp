<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>

<body>
    <div id="container">
        <h3>Operations</h3>

        <a href="<c:url value='/balance'/>">Balance</a><br/>
        <a href="<c:url value='/withdrawal'/>">Remove sum</a><br/>

        <form action=" <c:url value='/j_spring_security_logout'/>" method="post">
            <input type="submit" value="Exit" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>

<jsp:include page="_foot.jsp"/>
