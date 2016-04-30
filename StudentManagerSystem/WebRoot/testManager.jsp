<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<!--  write by uchiyou@sina.com, -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testManager.jsp' starting page</title>
    
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
<title>testManager</title>
<style type="text/css">

body{
	margin:0;
	padding:0;
	font-size:14px;
	font-family:"微软雅黑","宋体";
}
.scoreRangeTableClass{
    color: white;
}
.distributeListClass{
    color: yellow;
}

</style>
</head>
<%@ page language="java" import="domain.*" %>
<%@ page language="java" import="java.util.ArrayList" %>
<%-- <jsp:useBean id="myCourse" scope="request" class="domain.CourseBean" /> --%>


<body style="background:url(${pageContext.request.contextPath }/img2/water.jpg);">
<center>
<div id="head">
<h1>考试管理</h1>

</div>
<font size="+2" color="#0000CC">${noneCourseInfo }</font><br/>

 <jsp:useBean id="course" scope="request" class="domain.CourseBean" />

<form id="chooseCourseFormId" action="${pageContext.request.contextPath }/servlet/TestManagerServlet" method="post">
<input type="hidden" name="chooseCourseRecord" value="choiceCourse"/>
<select name="chooseCourse">
<!-- if we use id like <select id="chooseCourse">
     then we can get the select value in the server

 -->
              <c:forEach var="course2" items="${courseList}">
               <jsp:useBean id="course2" scope="request" class="domain.CourseBean" />              
            <option value="<jsp:getProperty property='course_number' name='course2'/>">
            <jsp:getProperty property="course_name" name="course2"/>
            </option> 
        </c:forEach>
</select>
<input type="submit" value="提交"/>
</form>



<br/><br/>

<%-- 


课程总平均分：<span id="courseScoreAverage">xxx</span><br/><br/>

<div id="konwledgeAnalyse">
<center>
<span id="a_courseId" class="course">
xxx</span>
课程分析总结
<listing>
<b>本试卷中共有 <span id="a_mainQuestionNumber">xxx</span> 道大题， <span id="a_detailQuestionNumber">xxx</span> 道小题；包含了大纲中 <span id="a_knowledgeInoutline">xxx</span> 知识点；
覆盖大纲知识点占（比例） <span id="a_proportionOfOutline">xxx</span> 。试题中难题占 <span id="a_proportionOfDifficultQuestion">xxx</span> ,适中题占 <span id="a_proportionOfTroublesomeQuestion">xxx</span> ,
基本知识题占 <span id="a_proportionOfBasicQuestion">xxx</span> ,试题中综合与提高性题目占 <span id="a_proportionOfIntegrateQestion">xxx</span> 。学生作对难题人数占比 <span id="a_proportionOfScoreDifficultQuestion">xxx</span> ,
做对基本知识题占 <span id="a_proportionOfScoreBasicQuestion">xxx</span> ,综合与提高题做对比例 <span id="a_proportionOfScoreIntegrateQuestion">xxx</span> 。
</b>
</listing> 
</center>
</div> --%>



<br/>

<div id="knowledgeDistribute">
  <br/>
<table id="knowledgeDistribute"width="800" border="1" class="distributeListClass" summary="this is a test
">
  <caption>
    <span id="courseId" class="course">${curCourseName }</span>课试卷知识点覆盖表 <!--  <input type="button" id="modifyKnowledgeDistribute" value="修改"/> -->
  </caption>
  <tr>
    <th scope="col">大题号</th>
    <th scope="col">小题号</th>
  <!--   <th scope="col">题目所属大纲章节</th> -->
    <th scope="col">标准题分</th>
    <th scope="col">难易程度</th>
    <th scope="col">题目类型</th>
  </tr>
  <c:forEach var="distribute" items="${distributeList}">
 <jsp:useBean id="distribute" scope="request" class="domain.KnowledgeDistributeBean" />
            <tr>    
                 <td><jsp:getProperty property="main_question_id" name="distribute"/></td>        
                 <td><jsp:getProperty property="detail_question_number" name="distribute"/></td>        
                 <%-- <td>
                 <c:forEach var="partInOutlineItem" items="<jsp:getProperty property='partInOutline' name='distribute'/>">
                       ${partInOutlineItem } &nbsp;
                  </c:forEach>
                 </td>  --%>       
                 <td><jsp:getProperty property="score" name="distribute"/></td>        
                 <td><jsp:getProperty property="easy_level" name="distribute"/></td>        
                 <td><jsp:getProperty property="type" name="distribute"/></td>                    
            </tr>        
        </c:forEach>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
   <!--  <td>&nbsp;</td> -->
  </tr>
</table>
</div>





<br/><br/>


<%-- 
<div id="queryScore">
<div id="averageQuery">
<br/><br/>

<table id="questionScoreAverage" width="800" border="1">
  <caption>
  <span id="detailQestionScore" class="course">${curCourseName }</span>
    题目得分统计
  </caption>
  <tr>
    <th scope="col">班级</th>
    <th scope="col">基本题做对比</th>
    <th scope="col">难题所做对比</th>
    <th scope="col">综合提高题做对比</th>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
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
  
  <tr>
    <th scope="col">课程总做对比</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
 --%>

<br/><br/><br/>
<table id="scoreSpanAverage" class="distributeListClass" width="800" border="1">
  <caption>
  <span id="scoreDistributeCourse" class="course">${curCourseName }</span>
    成绩区间分布统计
  </caption>
  <tr>
    <th scope="col">班级</th>
    <th scope="col">A[86-100]</th>
    <th scope="col">B[71-85]</th>
    <th scope="col">C[61-70]</th>
    <th scope="col">D[0-59]</th>
    <!-- <th scope="col">平均分</th> -->
  </tr>
 
  <c:forEach var="score" items="${scoreList}">
 <jsp:useBean id="score" scope="request" class="domain.ScoreRangeBean" />
            <tr>   
                 <td><jsp:getProperty property="class_number" name="score"/>
                 </td>        
                 <td><jsp:getProperty property="a" name="score"/></td>        
                 <td><jsp:getProperty property="b" name="score"/></td>        
                 <td><jsp:getProperty property="c" name="score"/></td>        
                 <td><jsp:getProperty property="d" name="score"/></td>        
              
            </tr> 
                 
        </c:forEach>
  
   <tr>
   
    <th scope="col">平均人数</th>
    <td id="AscoreEnd">${aa}</td>
    <td id="BscoreEnd">${ba }</td>
    <td id="CscoreEnd">${ca }</td>
    <td id="DscoreEnd">${da }</td>
    <%-- <td id="scoreClassAverageEnd">${(aa+ba+ca+da)/4}</td> --%>
  </tr>
</table>

<a href="${pageContext.request.contextPath }/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a>

</center>
</body>
</html>
 