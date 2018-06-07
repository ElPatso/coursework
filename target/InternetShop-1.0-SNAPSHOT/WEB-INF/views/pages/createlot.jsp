<%@include file="../tiles/layouts/library.jsp" %>

<div id="page">
    <h2>Make publication</h2>
    <div id="imgContainer" class="imagecreatelot">
        <img src="${pageContext.request.contextPath}/resources/img/800x600.png">
    </div>
    <div class="contentcreatelot">
        <form:form method="post" enctype="multipart/form-data" modelAttribute="publication">
            <form:input path="publicationTitle" type="text" placeholder="Enter publication title"/>

            <p><form:textarea rows="4" cols="57" path="description" type="text" placeholder="Enter description"/></p>

            <label>Public: </label><form:radiobutton path="privatePublication" value="false" onclick="hideGroup()"/>
            <label>Private: </label><form:radiobutton path="privatePublication" value="true" onclick="showGroup()"/>

            <div class="checkbox-div" id="selectGroup">
                <c:forEach var="item" items="${groups}">
                    <span class="checkbox"><form:checkbox path="groupList" value="${item}"/>${item}</span>
                </c:forEach>
            </div>
            </br>
            <div class="fileUpload btn btn-success">
                <span>Upload logo</span>
                <form:input path="logo" type="file" onchange="uploadImage()" id="uploadimage" class="upload"/>
            </div>
            </br>
            </br>
            <form:input path="file" type="file" title="Upload"/>
            </br>
            <input type="submit" value="Submit" class="btn btn-success"/>
        </form:form>
    </div>
    <div class="clear"></div>


</div>