<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- for function manager teacher's information,why just teacher,because this my part. write by uchiyou@sina.com, -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
 --%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>personManager</title>
</head>

<%@ page language="java" import="domain.*" %>
<body style="background:url(${pageContext.request.contextPath }/img2/girl.jpg);">
<center>
<div id="head">
<h1>个人管理</h1>
</div>

<div id="person">
</div>
<form action="${pageContext.request.contextPath }/servlet/PersonManagerServlet" method="post">
<table id="personInfo" width="500" border="1">
  <caption>
    <span id="personName">${wageNumber }
    </span>个人信息
  </caption>
  <tr>
    <th scope="col">
   密码   </th>
    <th scope="col">
   姓名
    </th>
    <th scope="col">
   职务</th>
  </tr>
  <tr>

    <td><textarea cols="20" rows="2" name="password" ${modify }>${password }</textarea></td>
    <td><textarea cols="20" rows="2" name="userName" ${modify }>${userName }</textarea></td>
   
    <td>
                 <select name="job">
                          <c:forEach var="job" items="${jobs}">
                           <option value="${job }">
                                ${job }
                              </option>
                          </c:forEach>                     
                 </select>
     </td>
   </tr>
    <tr>
    <td>
      <input type="hidden" name="userModify" value="userModify"/>
    <input type="submit" id="submit" name="modifyCommit" value="${modifyCommit }"/></td>
    <td>${modifyErrorInfo }</td>
  </tr>
</table>
</form>


<br/>
<a href="${pageContext.request.contextPath }/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a>

</center>
</body>
</html>
 