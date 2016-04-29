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
<br/>
<div id="operation">
<div id="query">
<font size="+2" color="#0000CC">${noneCourseInfo }</font><br/>
<font size="+2" color="#CCCC00">学生信息查询</font><br/>
<form action="${pageContext.request.contextPath }/servlet/StudentManageServlet" method="post">
请输入学生学号：<input type="text" id="stduentNumber" name="studentNumber1"></input>
<input type="hidden" name="smpart" value='1' />
<input type="submit" id="submit" value="确定" />
<font color="#CCCCCC">学生学号如13130110075</font>
<br/><font color="0x005500">${studentNumber1Info }</font><br/>





<div id="stuInfo"  style="display:${displayStuInfo}">
<table id="showStudent">
<jsp:useBean id="studentBean" scope="request" class="domain.StudentBean" />
            <tr>
    <th scope="col">学号</th>
    <th scope="col">班级</th>
    <th scope="col">姓名</th>
    <th scope="col">性别</th>
    <th scope="col">学生类型</th>
  </tr>
            <tr>    
                 <td><jsp:getProperty property="stu_number" name="studentBean"/></td>        
                 <td><jsp:getProperty property="class_number" name="studentBean"/></td>        
                 <td><jsp:getProperty property="name" name="studentBean"/></td>                
                 <td><jsp:getProperty property="sex" name="studentBean"/></td>        
                 <td><jsp:getProperty property="stu_type" name="studentBean"/></td>               
            </tr> 
</table></div>   
</form>
</div>

<br/>
<br/>


<div id="modify">
<font size="+2" color="#CCCC00">学生成绩查询</font><br/>
<br/>
<form action="${pageContext.request.contextPath }/servlet/StudentManageServlet" method="post">
<input type="hidden" name="smpart" value='2' />
选择课程<br/>
<select name="chooseCourse" onchange="chooseCourse()">
                          <c:forEach var="courseBean" items="${courseList}">
 <jsp:useBean id="courseBean" scope="request" class="domain.CourseBean" />              
                         <option value="<jsp:getProperty property='course_number' name='courseBean'/>">
                         <jsp:getProperty property="course_name" name="courseBean"/>
                         </option>
        </c:forEach>                     
</select>
<br/><br/>
请输入班级号
：<input type="text" id="ClassNumber" name="ClassNumber" />
<input type="submit" id="submit" value="确定" /><font color="0x008800">${classInfo}</font>
</form>



<font color="#CCCCCC">如果不输入班级号如：13011，表示查询已选课程所有学生成绩</font>
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
<br/>
<a href="${pageContext.request.contextPath }/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a>

</center>
</body>
</html>