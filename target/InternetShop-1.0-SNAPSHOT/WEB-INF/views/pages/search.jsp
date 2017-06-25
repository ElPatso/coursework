<%@include file="../tiles/layouts/library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div id="page" class="b3radius">
<c:if test="${empty products}">
    No matches
</c:if>
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/lot/${product.id}">

                        <c:if test="${not empty product.image }">
                            <img class="image" src="${contextPath}/resources/img/1.jpg">
                        </c:if>

                    </a>
                    <div class="caption">
                        <a href = "${pageContext.request.contextPath}/lot/${product.id}"><h3>${product.name}</h3></a>
                        <p>price: $${product.price}</p>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
