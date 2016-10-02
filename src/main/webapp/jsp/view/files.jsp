<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>All files</title>
</head>
<body>
<!-- DOESN'T WORK -->
<c:forEach items="${files}" var="file">
    <tr>
        <td>File ID: ${file.id}</td>
        <td>File Name: ${file.fileName}</td>  
    </tr>
</c:forEach>
</body>
</html>