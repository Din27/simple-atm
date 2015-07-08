<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_head.jsp"/>

    <c:set var="errorMsg" value="${errorMsg == null ? 'Error' : errorMsg}"/>
    <div class="error">${errorMsg}</div>
    <a href="<c:url value='/'/>">Back</a><br/>

<jsp:include page="_foot.jsp"/>