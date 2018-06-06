<%@include file="../layouts/library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="header">
    <a href="${contextPath}/"><img src="${contextPath}/resources/img/logo.png" class="logoimg">
    </a>
    <%--<h1>CAD Library</h1>--%>
    <div></div>
    <div class="searchDiv">
        <form action="${contextPath}/search" method="post" class="search" >
            <input path="searchRow"  type="search" name="searchRow" id="textsearch" onkeyup="checkparam()" placeholder="Search..." class="input" />
            <input type="submit" disabled="disabled" name="" value="" class="submit" id="submit"/>
        </form>
    </div>
    <div class="buttonsign">
        <security:authorize access="isAnonymous()">
        <a href="${contextPath}/login"><button >Sign In</button></a>
        <a href="${contextPath}/registration"><button>Sign Up</button></a>
        </security:authorize>

        <security:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_LECTURER')">
            <a href="${contextPath}/cart"><button >Cart</button></a>
            <a href="${contextPath}/logout"><button>Logout</button></a>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href="${contextPath}/editusers"><button >Edit Users</button></a>
            <a href="${contextPath}/editcategory"><button >Create group</button></a>
            <a href="${contextPath}/createlot"><button >Make public.</button></a>
            <a href="${contextPath}/logout"><button>Logout</button></a>
        </security:authorize>
    </div>

</div>
