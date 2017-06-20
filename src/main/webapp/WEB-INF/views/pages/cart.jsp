<%@include file="../tiles/layouts/library.jsp"%>

<div id="page" class="b3radius">
    <c:choose>
        <c:when test="${empty cart}">
            Корзина пуста
        </c:when>
    <c:otherwise>
<table class="simple-little-table" cellspacing='0'>
    <tr>
        <th>Назва товару</th>
        <th>Ціна</th>
        <th></th>

    </tr><!-- Table Header -->
<c:forEach var="lot" items="${cart}">
    <tr>
        <td><a href="#">${lot.name}</a></td>
        <td>${lot.price}</td>
        <td>
            <a href="${pageContext.request.contextPath}/cart/delete/${lot.id}">Delete</a><br/>
        </td>
    </tr><!-- Table Row -->
</c:forEach>
</table>
    </c:otherwise>
    </c:choose>
</div>