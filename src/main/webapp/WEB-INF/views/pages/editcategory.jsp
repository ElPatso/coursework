<%@include file="../tiles/layouts/library.jsp"%>
<div id="page">
    <h2>Create category</h2>
    <div id="result" style="text-align: center"></div>
    <div class="categories">
        <ul >
            <c:forEach var="menu" items="${categories}">
                <li>
                    <a href="#">${menu.name}</a>
                    <input type="image" onclick="addToInput(${menu.id})" src="${pageContext.request.contextPath}/resources/img/plus.png" width="12" height="12"/>
                    <input type="image" onclick="deleteCategory(${menu.id})" src="${pageContext.request.contextPath}/resources/img/remove.png" width="12" height="12"/>
                    <c:if test="${menu.categorySet!= null}">
                        <ul>
                            <c:forEach var="submenu" items="${menu.categorySet}">

                                <li>
                                    <a href="#">${submenu.name}</a>
                                    <input type="image" onclick="addToInput(${submenu.id})" src="${pageContext.request.contextPath}/resources/img/plus.png" width="12" height="12"/>
                                    <input type="image" onclick="deleteCategory(${submenu.id})" src="${pageContext.request.contextPath}/resources/img/remove.png" width="12" height="12"/>

                                    <c:if test="${submenu.categorySet !=null}">
                                        <ul>
                                            <c:forEach var="submenu2" items="${submenu.categorySet}">
                                                <li>
                                                    <a href="#">${submenu2.name}</a>
                                                    <input type="image" onclick="deleteCategory(${submenu2.id})" src="${pageContext.request.contextPath}/resources/img/remove.png" width="12" height="12"/>
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
    <label>Create root category</label>
    <button onclick="addToInput(0)">Create</button>
    <form action="${pageContext.request.contextPath}/editcategory/" id="category">
        <input id="id" type="hidden">
        <input type="text" id="categoryname" >
        <button type="submit" id="categorysubmit"  hidden>Add</button>
    </form>
</div>
    <div class="clear"></div>
</div>