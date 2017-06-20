<%@include file="../layouts/library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="header">
    <a href="${contextPath}/"><img src="${contextPath}/resources/img/shop.png" class="logoimg">
    </a>
    <h1>Sombra's Internet shop</h1>
    <div></div>
    <div class="searchDiv">
        <form:form action="${contextPath}/search" method="post" class="search" modelAttribute="search">
            <form:input path="searchRow" type="search" name="" placeholder="пошук..." class="input" />
            <input type="submit" name="" value="" class="submit" />
        </form:form>
    </div>
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <div class="buttonsign">
        <security:authorize access="isAnonymous()">
        <a href="${contextPath}/login"><button >Sign In</button></a>
        <a href="${contextPath}/registration"><button>Sign Up</button></a>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_USER')">
            <a href=""><button >Profile</button></a>
            <a href="${contextPath}/cart"><button >Cart</button></a>
            <a onclick="document.forms['logoutForm'].submit()"><button>Logout</button></a>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href=""><button >Edit Users</button></a>
            <a href="${contextPath}/createLot"><button >Create lot</button></a>
            <a onclick="document.forms['logoutForm'].submit()"><button>Logout</button></a>
        </security:authorize>


    </div>

</div>
