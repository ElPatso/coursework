<%@ include file="../tiles/layouts/library.jsp"%>

<div id="page" class="b3radius">
<div class="media">
    <img src="/resources/img/${product.id}.png">
    <security:authorize access="hasRole('ROLE_USER')">
        <c:if test="${!execute}">
    <p><button id="addToCart" class="button7" onclick="addtoCart(${product.id})">Add to cart</button></p>
        </c:if>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_ADMIN')">
    <div class="admbuttons">
        <a class="knopka" href="${pageContext.request.contextPath}/editlot/${product.id}">Change lot</a>
        <a class="knopka" href="${pageContext.request.contextPath}/remove/${product.id}">Remove lot</a>
    </div>
    </security:authorize>
</div>
<div class="body">
    <P><h2>${product.name}</h2></P>
    <p>${product.category}</p>
    <p>${product.price}</p>
    <p>${product.description}</p>
</div>
<div class="clear"></div>
<p><center><h3>Reviews:</h3></center></p>
<table id="table_grid" class="commentTable">
    <c:forEach var="comment" items="${product.commentsSet}">
    <tr>
        <td rowspan="2" width="65"><img src="${pageContext.request.contextPath}/resources/img/user_img.png" width="64" height="64" ></td>
        <th colspan="2">${comment.name} left a review :</th>
    </tr>
    <tr>
      <th> <p>${comment.comment}</th>
    </tr>

    </c:forEach>

</table>
    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <div class="commentsForm">
        <p><b>Leave a review:</b></p>
        <form method="post" action="${pageContext.request.contextPath}/lot/${product.id}/addComment" id="btnSubmitComment" >

            <input  type="hidden" id="name" value="${pageContext.request.userPrincipal.name}"/>
            <textarea  id="comment" cols="45" rows="5"></textarea><br>

            <input type="submit" value="Add comment" class="button7"/>

        </form>
</div>
    </security:authorize>
</div>
