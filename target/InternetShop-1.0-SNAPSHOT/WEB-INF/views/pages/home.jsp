<%@include file="../tiles/layouts/library.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div id="page" class="b3radius">

    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/lot/${product.id}">
                        <img class="image" src="/resources/img/${product.logo}">
                    </a>
                    <div class="caption">
                        <a href="${pageContext.request.contextPath}/lot/${product.id}"><h3>${product.title}</h3></a>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <tag:paginate offset="${offset}" count="${count}"
                  uri="${contextPath}/" next="&raquo;" previous="&laquo;"/>
</div>
