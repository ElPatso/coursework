<%@ include file="../tiles/layouts/library.jsp" %>

<div id="page" class="b3radius">
    <div class="media">
        <img src="${pageContext.request.contextPath}/resources/img/${product.logo}">
    </div>
    <div>
        <form:form method="post" id="rateForm" modelAttribute="rating" cssClass="rating">
            <form:radiobutton disabled="${rating.authority}" path="value" id="star5" name="rating" value="5" onclick="rate()"/><label class="full"
                                                                                                       for="star5"
                                                                                                       title="Awesome - 5 stars"></label>
            <form:radiobutton disabled="${rating.authority}" path="value" id="star4" name="rating" value="4" onclick="rate()"/><label class="full"
                                                                                                       for="star4"
                                                                                                       title="Pretty good - 4 stars"></label>
            <form:radiobutton disabled="${rating.authority}" path="value" id="star3" name="rating" value="3" onclick="rate()"/><label class="full"
                                                                                                       for="star3"
                                                                                                       title="Meh - 3 stars"></label>
            <form:radiobutton disabled="${rating.authority}" path="value" id="star2" name="rating" value="2" onclick="rate()"/><label class="full"
                                                                                                       for="star2"
                                                                                                       title="Kinda bad - 2 stars"></label>
            <form:radiobutton disabled="${rating.authority}" path="value" id="star1" name="rating" value="1" onclick="rate()"/><label class="full"
                                                                                                       for="star1"
                                                                                                       title="Sucks big time - 1 star"></label>
            <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STUDENT','ROLE_LECTURER')">
                <input type="submit" value="Rate" class="btn btn-success" id="rateButton"/>
            </security:authorize>
        </form:form>
    </div>
    <div class="body">
        <P>
        <h2>${product.title}</h2></P>
        <p>${product.uploadedAt}</p>
        <p>${product.uploadedBy}</p>
        <form method="get" action="${pageContext.request.contextPath}/lot/download/${product.id}/">
            <input type="submit" value="Download" class="button7"/>
        </form>
    </div>
    <div class="clear"></div>
    <p>
    <center><h3>Reviews:</h3></center>
    </p>
    <table id="table_grid" class="commentTable">
        <c:forEach var="comment" items="${product.commentsList}">
            <tr>
                <td rowspan="2" width="65"><img src="${pageContext.request.contextPath}/resources/img/user_img.png"
                                                width="64" height="64"></td>
                <th colspan="2">${comment.name} left a review :</th>
            </tr>
            <tr>
                <th><p>${comment.comment}</th>
            </tr>

        </c:forEach>

    </table>
    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
        <div class="commentsForm">
            <p><b>Leave a review:</b></p>
            <form method="post" action="${pageContext.request.contextPath}/lot/${product.id}/addComment"
                  id="btnSubmitComment">

                <input type="hidden" id="name" value="${pageContext.request.userPrincipal.name}"/>
                <textarea id="comment" cols="45" rows="5"></textarea><br>

                <input type="submit" value="Add comment" class="button7"/>

            </form>
        </div>
    </security:authorize>
</div>
