<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error page</title>
</head>
<body>
<center>
不好意思,好像粗错鸟~~
<br/>
${errorInfo}
<br/>

<a href="${pageContext.request.contextPath}/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a>

</center>
</body>
</html>