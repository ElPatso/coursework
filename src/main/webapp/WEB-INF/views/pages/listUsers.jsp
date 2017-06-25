<%@ include file="../tiles/layouts/library.jsp"%>

<div id="page">
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>Username</td>
            <td>Email</td>
            <td>Status</td>
            <td>Action</td>
        </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tbody>
            <tr >
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td id="user_${user.id}">
                     <a href="#" onclick="changeStatus(${user.id},${user.enabled})">${user.enabled}</a>

                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/editusers/delete/${user.id}">Delete</a><br>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>

</div>