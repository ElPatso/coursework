<%@include file="../tiles/layouts/library.jsp" %>
<div id="page">
    <h2>Groups</h2>
    <div id="result" style="text-align: center"></div>
    <div class="categories">
        <table class="table table-bordered" id="groups">
            <thead>
            <tr>
                <td>Group name</td>
                </tr>
            </thead>
            <c:forEach var="group" items="${groups}">
                <tbody>
                <tr>
                    <td>${group.name}</td>

                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>

    <div class="addcategory">
        <label>Create new group</label>
        <form action="${pageContext.request.contextPath}/editcategory/" id="category">
            <input type="text" id="categoryname">
            <button type="submit" id="categorysubmit">Create</button>
        </form>
    </div>
    <div class="clear"></div>
</div>