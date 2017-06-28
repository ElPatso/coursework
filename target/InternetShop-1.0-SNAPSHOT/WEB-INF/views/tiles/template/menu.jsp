<%@include file="../layouts/library.jsp"%>
<div id="menu">
<nav>
        <ul >
            <c:forEach var="menu" items="${show}">
                <li>
                    <a href="#" class="disablet">${menu.name}</a>
                    <c:if test="${menu.categorySet!= null}">
                        <ul>
                            <c:forEach var="submenu" items="${menu.categorySet}">

                                <li>
                                    <a href="${pageContext.request.contextPath}/category?name=${submenu.name}">${submenu.name}</a>
                                    <c:if test="${submenu.categorySet !=null}">
                                        <ul>
                                            <c:forEach var="submenu2" items="${submenu.categorySet}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/category?name=${submenu2.name}">${submenu2.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>

                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
</nav>

</div>