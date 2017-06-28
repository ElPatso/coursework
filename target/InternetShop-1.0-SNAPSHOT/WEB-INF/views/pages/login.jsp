<%@include file="../tiles/layouts/library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- the Sign IN form -->


    <!-- Modal Content -->
    <form class="modal-content animate" method="POST" action="${contextPath}/login" >
        <div class="imgcontainer">
            <img src="${contextPath}/resources/img/img_avatar2.png" alt="Avatar" class="avatar">
        </div>

        <div class="form">
        <span>${error}</span><br>

            <label><b>Username</b></label>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true" required/>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>

        </div>

    </form>
