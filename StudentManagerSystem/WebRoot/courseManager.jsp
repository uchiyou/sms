
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- for function manager teacher's course, write by uchiyou@sina.com, -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>courseManager</title>
<style type="text/css">

body{
	margin:0;
	padding:0;
	font-size:14px;
	font-family:"微软雅黑","宋体";
}
.characterTableClass{
   color: green;
}

</style>
</head>


<%@ page language="java" import="domain.*" %>
<%-- 
<jsp:useBean id="courseRecordList" scope="request" class="domain.CourseRecordBean" />

 --%><%@ page language="java" import="java.util.ArrayList" %>
 


<body style="background:url(${pageContext.request.contextPath }/img/flower.jpg);">


<center>
<div id="head">
<h1>
课程管理
</h1>
</div>
<font size="+2" color="#0000CC">${noneCourseInfo }</font><br/>
<div id="mycourseDiv">

<table id="courseInfo" width="600" border="1" class="characterTableClass">
  <caption>
    ${curUser }课程
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
 <c:forEach var="course" items="${courseList}">
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




<form id="chooseCourseFormId" action="${pageContext.request.contextPath }/servlet/CourseManagerServlet" method="post">
<input type="hidden" name="chooseCourseRecord" value="choiceCourse"/>
<select name="chooseCourse">
                        <c:forEach var="courseBean" items="${courseList}">
                        <jsp:useBean id="courseBean" scope="request" class="domain.CourseBean" />              
                         <option value="<jsp:getProperty property='course_number' name='courseBean'/>">
                         <jsp:getProperty property="course_name" name="courseBean"/>
                         </option>
        </c:forEach>            
</select>
<input type="submit" value="确认"/>
</form>





<form id="insertCourseRecord" action="${pageContext.request.contextPath }/servlet/CourseManagerServlet" method="post">

<table width="600" border="1" class="characterTableClass">
  <caption>
  <span id="record_courseId" class="course"></span>
                         ${curCourseName }课程实施记录
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
    <td><textarea name="orderNumber" rows="10" readonly="readonly">${recordSequence }</textarea></td>
    <td><textarea cols="60" rows="10" name="teacherContent"></textarea></td>
    <td>
    <select name="courseType">
    <!-- set('lecture','solve exercise','course test','review')  -->
                         <option value="lecture">讲授</option>
                         <option value="solve exercise">习题讲解</option>
                         <option value="course test">随堂检测</option>
                         <option value="review">复习课程</option>                         
</select>
    </td>
  </tr>
   <tr>  
    <td>
      <input type="hidden" name="courseRecordHidden" value="courseRecordHidden"/>
      <input type="hidden" name="courseNumber2" value="${curCourse }"/>
      <input type="hidden" name="curCourseRecordId" value="${curCourseRecordId }"/>
    <input type="submit" id="submit" value="确认插入" />   
    </td>
    <td><font color="0x006600">
    ${insertCourseRecordInfo } 
    </font>
    </td>
  </tr>
</table>
</form>

</div>

<a href="${pageContext.request.contextPath }/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a>


</center>
</body>
</html>
 