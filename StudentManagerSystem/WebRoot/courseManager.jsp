<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>courseManager</title>
</head>


<%@ page language="java" import="domain.*" %>
<%-- 
<jsp:useBean id="courseRecordList" scope="request" class="domain.CourseRecordBean" />

 --%><%@ page language="java" import="java.util.ArrayList" %>
<%-- <jsp:useBean id="myCourse" scope="request" class="domain.CourseBean" /> --%>


<body>
<center>
<div id="head">
<h1>
课程管理
</h1>
</div>

<div id="mycourse">

<table id="courseInfo" width="600" border="1">
  <caption>
    我的课程
  </caption>
  <tr>
    <th scope="col">课程名称</th>
    <th scope="col"> 课程号 </th>
    <th scope="col">课程性质</th>
    <th scope="col">课程学时</th>
    <th scope="col">课程学分</th>
    <th scope="col">课程类型</th>
    <th scope="col">开设方向</th>
  </tr>
<!-- 遍历一个集合里面的javabean   -->
 <c:forEach var="course" items="${myCourse}">
 <jsp:useBean id="course" scope="request" class="domain.CourseBean" />
            <tr>    
                 <td><jsp:getProperty property="course_name" name="course"/></td>        
                 <td><jsp:getProperty property="course_number" name="course"/></td>        
                 <td><jsp:getProperty property="course_type" name="course"/></td>        
                 <td><jsp:getProperty property="course_duration" name="course"/></td>        
                 <td><jsp:getProperty property="course_score" name="course"/></td>        
                 <td><jsp:getProperty property="course_student" name="course"/></td>        
                 <td><jsp:getProperty property="course_direction" name="course"/></td>        
            </tr>        
        </c:forEach>
</table>
</div>

<br/>
<br/>
<br/>




<div id="courseRecord">
<select id="chooseCourse">
                         
</select>
<table width="400" border="1">
  <caption>
  <span id="record_courseId" class="course"></span>
    课程实施记录
  </caption>
  <tr>
    <th scope="col">序号</th>
    <th scope="col">讲授内容</th>
    <th scope="col">课程形式</th>
  </tr>
  
 <c:forEach var="record" items="${courseRecord}">
 <jsp:useBean id="record" scope="request" class="domain.CourseRecordBean" />
            <tr>    
                 <td><jsp:getProperty property="sequence" name="record"/></td>        
                 <td><jsp:getProperty property="course_content" name="record"/></td>        
                 <td><jsp:getProperty property="type" name="record"/></td>                
            </tr>        
        </c:forEach>
  
  <tr>
    <td>请输入最新课程记录</td>
  </tr>
   <tr>
    <td><input type="text" id="orderNumber" /></td>
    <td><input type="text" id="teacherContent"/></td>
    <td>
    <select id="courseType">
                         <option value="teach">讲授</option>
                         <option value="exercise">习题讲解</option>
                         <option value="quit">随堂检测</option>
                         <option value="review">复习课程</option>
                         
</select>
    </td>
  </tr>
   <tr>
    <td><input type="submit" id="submit" text="提交" /></td>
  </tr>
</table>

</div>


</center>
</body>
</html>
 