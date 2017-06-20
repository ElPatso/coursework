<%@include file="../tiles/layouts/library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- the Sign Up form -->
<form:form method="POST" modelAttribute="userForm" class="modal-content animate" action="${contextPath}/registration">
        <div class="form">
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
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
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control"
                                placeholder="email"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            <div class="clearfix">
                <button type="submit" class="signupbtn">Sign Up</button>
                <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
            </div>
        </div>
    </form:form>

  