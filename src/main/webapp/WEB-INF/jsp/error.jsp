<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>
<body>
    <div id="container">
        <c:set var="errorMsg" value="${errorMsg == null ? 'Error' : errorMsg}"/>
        <div class="error">${errorMsg}</div>
    </div>
</body>
<jsp:include page="_foot.jsp"/>