<%@include file="../tiles/layouts/library.jsp"%>

<div id="page">
    <h2>Edit lot</h2>
    <div id="imgContainer" class="imagecreatelot">
        <img src="/resources/img/${lot.id}.png">
    </div>
    <div class="contentcreatelot">
        <form:form method="post" enctype="multipart/form-data" modelAttribute="lot">
            <form:input path="name" type="text"  placeholder="Enter product name"/>
            <form:select path="category" type="text" >
                <form:option value="NONE" label="-----Select category-----"/>
                <form:options items="${categories}"/>
            </form:select>
            <form:input path="price" type="text" placeholder="Enter product price"/>
            <p><form:textarea rows="4" cols="57" path="description" type="text" placeholder="Enter description"/></p>
            <div class="fileUpload btn btn-success">
                <span>Upload</span>
                <form:input path="image" type="file" onchange="uploadImage()" id="uploadimage" class="upload"/>
            </div>
            <input type="submit" value="Submit" class="btn btn-success" />
        </form:form>
    </div>
    <div class="clear"></div>


</div>