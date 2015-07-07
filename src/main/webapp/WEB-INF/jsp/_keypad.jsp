<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="keypad">
    <script src="/js/keypad.js"></script>
    <c:set var="keys">1,2,3,4,5,6,7,8,9,0</c:set>
    <c:forTokens var="key" items="${keys}"
                 delims=",">
        <button class="keypad-key" type="button" value="<c:out value='${key}'/>" ><c:out value='${key}'/></button>
    </c:forTokens>
</div>