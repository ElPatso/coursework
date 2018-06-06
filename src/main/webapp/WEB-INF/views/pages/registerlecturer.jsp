<%@include file="../tiles/layouts/library.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- the Sign Up form -->
<form:form method="POST" modelAttribute="userForm" class="modal-content animate" action="${contextPath}/registerLecturer">
    <div class="form">
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control"
                                placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>

        <div class="clearfix">
            <button type="submit" class="signupbtn">Sign Up</button>
        </div>
    </div>
</form:form>

  