<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<body>
<center>
<div id="head">
<h1>个人管理</h1>
</div>

<div id="person">
</div><table id="personInfo" width="500" border="1">
  <caption>
    <span id="personName">
    </span>个人信息
  </caption>
  <tr>
    <th scope="col">
   工资号   </th>
    <th scope="col">
   姓名
    </th>
    <th scope="col">
   职务</th>
  </tr>
  <tr>
    <td><textarea cols="20" rows="2" name="wageNumber" rows="10" ${modify }>${wageNumber }</textarea></td>
    <td><textarea cols="20" rows="2" name="userName" rows="10" ${modify }>${userName }</textarea></td>
    <td><textarea cols="20" rows="2" name="job" rows="10" ${modify }>${job }</textarea></td>   
  </tr>
  <tr>
  
  <center>
    <td border="0"><input type="submit" id="submit" name="modify" value="修改"/></td>
    </center>
  </tr>
</table>




</center>
</body>
</html>
 