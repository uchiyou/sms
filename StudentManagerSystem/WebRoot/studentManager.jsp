<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'studentManager.jsp' starting page</title>
    
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
<title>studentManager</title>
</head>
<%@ page language="java" import="domain.*" %>
<%@ page language="java" import="java.util.ArrayList" %>

<body>
<center>
<div id="head1">
<h1 id="head1">学生管理</h1>
</div>

<div id="operation">
<div id="query">
<font size="+2" color="#CCCC00">学生信息查询</font><br/>
请输入学生学号：<input type="text" id="stduentNumber" name="studentNumber" />
<input type="submit" id="submit" value="确定" />
<table id="studentInfo">
</table>

</div>

<br/>
<br/>


<div id="modify">
<font size="+2" color="#CCCC00">学生成绩查询</font><br/>
<br/>
<select id="chooseCourse" onchange="chooseCourse()">
                         <option value="">选择课程</option>
                         <option value="database">数据库</option>
                         
</select>
<br/><br/>
请输入
<select id="stuOrClass" onchange="stuOrCLass">
                         <option value="classNumber">班级号</option>
                         <option value="stuNumber">学号</option>
                         
</select>
：<input type="text" id="stuOrClassNumber" name="stuOrClassNumber" />
<input type="submit" id="submit" value="确定" />
<font color="#CCCCCC">如果不输入学号和班级号，表示查询已选课程所有学生成绩</font>
<br/><br/>
<table id="studentInfo" width="800" border="1">
  <caption>
  <span id="courseId">
  <jsp:useBean id="defaultCourse" scope="request" class="domain.CourseBean" />
  <jsp:getProperty property="course_name"  name="defaultCourse"/>
  </span>课程
    <span id="stuOrClassName"></span>
    成绩表
  </caption>
  <tr>
    <th scope="col">学号</th>
    <th scope="col">平时作业成绩</th>
    <th scope="col">大作业成绩</th>
    <th scope="col">出勤成绩</th>
    <th scope="col">平时成绩</th>
    <th scope="col">考试成绩</th>
  </tr>
  <c:forEach var="scoreBean" items="${scoreList}">
 <jsp:useBean id="scoreBean" scope="request" class="domain.StudentCourseScoreBean" />
            <tr>    
                 <td><jsp:getProperty property="stu_number" name="scoreBean"/></td>        
                 <td><jsp:getProperty property="ordinary_work_score" name="scoreBean"/></td>        
                 <td><jsp:getProperty property="main_work_score" name="scoreBean"/></td>                
                 <td><jsp:getProperty property="attendance_score" name="scoreBean"/></td>        
                 <td><jsp:getProperty property="orderScore" name="scoreBean"/></td>        
                 <td><jsp:getProperty property="test_score" name="scoreBean"/></td>                
            </tr>        
        </c:forEach>
</table>

</div>

</div>


</center>
</body>
</html>