<%@ include file="../tiles/layouts/library.jsp"%>

<div id="page" class="b3radius">
<div class="media">
    <img src="${pageContext.request.contextPath}/resources/img/1.jpg">
    <security:authorize access="hasRole('ROLE_USER')">
        <c:if test="${!execute}">
    <p><button id="addToCart" class="button7" onclick="addtoCart(${product.id})">Добавити в корзину</button></p>
        </c:if>
    </security:authorize>
</div>
<div class="body">
    <P><h2>${product.name}</h2></P>
    <p>${product.price}</p>
    <p>${product.description}</p>
</div>
<div class="clear"></div>
<p><center>Відгуки:</center></p>
<table id="table_grid" class="commentTable">
    <c:forEach var="comment" items="${product.commentsSet}">
    <tr>
        <td rowspan="2" width="65"><img src="${pageContext.request.contextPath}/resources/img/user_img.png" width="64" height="64" ></td>
        <th colspan="2">${comment.name} Залишив відгук :</th>
    </tr>
    <tr>
      <th> <p>${comment.comment}</th>
    </tr>

    </c:forEach>

</table>
    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <div class="commentsForm">
        <p><b>Залиште відгук:</b></p>
        <form method="post" action="${pageContext.request.contextPath}/lot/${product.id}/addComment" id="btnSubmitComment" >

            <input  type="hidden" id="name" value="${pageContext.request.userPrincipal.name}"/>
            <textarea  id="comment" cols="45" rows="5"></textarea><br>

            <input type="submit" value="Create" />

        </form>
</div>
    </security:authorize>
</div>
