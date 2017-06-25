<%@include file="../tiles/layouts/library.jsp"%>
<div id="page">
    <div class="categories">
        <ul >
            <c:forEach var="menu" items="${categories}">
                <li>
                    <a href="#">${menu.name}</a>
                    <c:if test="${menu.categorySet!= null}">
                        <ul>
                            <c:forEach var="submenu" items="${menu.categorySet}">

                                <li>
                                    <a href="#">${submenu.name}</a>
                                    <c:if test="${submenu.categorySet !=null}">
                                        <ul>
                                            <c:forEach var="submenu2" items="${submenu.categorySet}">
                                                <li>
                                                    <a href="#">${submenu2.name}</a>
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
    </div>

<div class="addcategory">
    <form >
        <input type="text">
        <input type="submit">
    </form>
</div>
    <div class="clear"></div>
</div>