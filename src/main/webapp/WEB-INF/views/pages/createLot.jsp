<%@include file="../tiles/layouts/library.jsp"%>
<div id="page">
        <div class="body">

            <form:form
                    action="${pageContext.request.contextPath}/index/saveUser"
                    enctype="multipart/form-data" method="post"  commandName="user">

                    <label for="name">Firstname</label> <form:errors path="firstName" cssStyle="color:#ff0000;" />
                    <form:input path="firstName" id="name" name="name" class="form-control" />


                    <label for="lastName">Lastname</label> <form:errors path="lastName" cssStyle="color:#ff0000;" />
                    <form:input path="lastName" id="lastname" name="lastname"
                                class="form-control"></form:input>


                    <label for="age">Age</label> <form:errors path="age" cssStyle="color:#ff0000;" />
                    <form:input path="age" id="age" name="age"
                                class="form-control"></form:input>


                    <label for="address">Address</label> <form:errors path="address" cssStyle="color:#ff0000;" />
                    <form:input path="address" id="address" name="address"
                                class="form-control"></form:input>


                    <label class="control-label" for="productImage">Upload Image</label>
                    <form:input type="file" path="userImage" id="userImage" name="userImage"
                                class="form:input-large"></form:input>

                    <input type="submit" value="Submit" class="btn btn-success" />

            </form:form>
    </div>
</div>