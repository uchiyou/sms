<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
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
</head>
<%@ page language="java" import="domain.*" %>
<%@ page language="java" import="java.util.ArrayList" %>
<%-- <jsp:useBean id="myCourse" scope="request" class="domain.CourseBean" /> --%>


<body>
<center>
<div id="head">
<h1>考试管理</h1>

</div>



<script type="text/javascript">
//这段javascript应该想服务端发送请求刷新页面
var course=[['选择课程'],
           ['海定区','朝阳区','东城区','西城区'],
		   ['长沙','邵阳','岳阳','湘潭'],
		   ['深圳','东莞','广州','佛山'],
		   ['西安','咸阳','安康','渭成']];
		   
		 
		 function chooseCourse(){
			   //获取两个个选择区
			   var choose=document.getElementById("chooseCourse");
			   var subsel=document.getElementById("subsel");
			   //获取用户所选择的省市
			   var index=sel.selectedIndex;
			     
			   //获取该区的城市数组，　　　　　　　　　　　数组直接赋值
			   var cs=course[index];

			   //遍历该数组，并把所有的城市添加到子选区中
			   for(var i=0;i<cs.length;i++){
				   //创建option节点
				   var opt=document.createElement("option");
				   opt.innerHTML=cs[i];
				   opt.value=cs[i];
				   
				   subsel.appendChild(opt);
			   }
		   }
				   
				   
			   
</script>

<select id="chooseCourse" onchange="chooseCourse()">
                         <option value="">选择课程</option>
                         <option value="database">数据库</option>
                         
</select>
<br/><br/>




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
</div>



<br/>

<div id="knowledgeDistribute">
  <br/>
<table id="knowledgeDistribute"width="800" border="1" summary="this is a test
">
  <caption>
    <span id="courseId" class="course">xxx</span>课试卷知识点覆盖表  <input type="buttion" id="modifyKnowledgeDistribute" value="修改"/>
  </caption>
  <tr>
    <th scope="col">大题号</th>
    <th scope="col">小题号</th>
    <th scope="col">所在大纲章节</th>
    <th scope="col">标准题分</th>
    <th scope="col">难易程度</th>
    <th scope="col">题目类型</th>
  </tr>
  <c:forEach var="distribute" items="${distributeList}">
 <jsp:useBean id="distribute" scope="request" class="domain.KnowledgeDistributeBean" />
            <tr>    
                 <td><jsp:getProperty property="main_question_id" name="distribute"/></td>        
                 <td><jsp:getProperty property="detail_question_number" name="distribute"/></td>        
                 <td><jsp:getProperty property="partInOutline" name="distribute"/></td>        
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
    <td>&nbsp;</td>
  </tr>
</table>
</div>





<br/><br/>



<div id="queryScore">
<div id="averageQuery">
<br/><br/>

<table id="questionScoreAverage" width="800" border="1">
  <caption>
  <span id="detailQestionScore" class="course">xxx</span>
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
 <%--  <c:forEach var="course" items="${myCourse}">
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
        </c:forEach> --%>
  
  <tr>
    <th scope="col">课程总做对比</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>


<br/><br/><br/>
<table id="scoreSpanAverage" width="800" border="1">
  <caption>
  <span id="scoreDistributeCourse" class="course">xxx</span>
    成绩区间分布统计
  </caption>
  <tr>
    <th scope="col">班级</th>
    <th scope="col">A[86-100]</th>
    <th scope="col">B[71-85]</th>
    <th scope="col">C[61-70]</th>
    <th scope="col">D[0-59]</th>
    <th scope="col">平均分</th>
  </tr>
 <%  int count=0,suma=0,sumb=0,sumc=0,sumd=0,sum=0; %>
  <c:forEach var="score" items="${scoreList}">
 <jsp:useBean id="score" scope="request" class="domain.ScoreRangeBean" />
            <tr>    
                 <td><jsp:getProperty property="class_number" name="score"/></td>        
                 <td><jsp:getProperty property="a" name="score"/></td>        
                 <td><jsp:getProperty property="b" name="score"/></td>        
                 <td><jsp:getProperty property="c" name="score"/></td>        
                 <td><jsp:getProperty property="d" name="score"/></td>        
                 <td>
                 
                 <% 
                 sum=(score.getA()+score.getB()+score.getC()+score.getD())/4;
                 count++;
                 suma+=score.getA();
                 sumb+=score.getB();
                 sumc+=score.getC();
                 sumd+=score.getD();
                  %>
                 
                 </td>   
            </tr>        
        </c:forEach>
  
   <tr>
   
    <th scope="col">课程总平均</th>
    <td id="AscoreEnd">${suma/count}</td>
    <td id="BscoreEnd">${sumb/count }</td>
    <td id="CscoreEnd">${sumc/count }</td>
    <td id="DscoreEnd">${sumd/count }</td>
    <td id="scoreClassAverageEnd">${sum/count}</td>
  </tr>
</table>

</div>


</div>

</center>
</body>
</html>
 