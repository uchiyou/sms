<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
<title>StudentManagerSystem</title>
<!--
<link href="css/uu.css" type="text/css" rel="stylesheet" />
<link href="css/element.css" type="text/css" rel="stylesheet" />
-->
<style type="text/css">

body{
	margin:0;
	padding:0;
	font-size:12px;
	font-family:"微软雅黑","宋体";
	background:url(../images/background3.jpg) repeat-x;
}


#head{
	position:absolute;
	top:0px;
	left:0px;
	width:1366px;
	height:200px;
	border:#3F0 0px none;
}
#register{
	 display:none;
	 position:absolute;
	right:150px;
	top:30px;
	width:250px;
	height:50px;
	background-color:#B0FD64;
}
#rightUp{
	position:absolute;
	right:30px;
	top:0px;
	width:450px;
	height:50px;
}
#main{
	position:absolute;
	top:215px;
	left:0px;
	width:1366px;
	height:500px;
	background-color:#CF6;
}
#main_1{
	position:absolute;
	left:0px;
	top:0px;
	width:800px;
	height:500px;
}
#testManager{
	position:absolute;
	left:0px;
	top:0px;
	width:400px;
	height:300px;
	background-color:#9C0;
}

#course{
	position:absolute;
	left:400px;
	top:0px;
	width:400px;
	height:300px;
	background-color:#90F;
}
#news{
	position:absolute;
	left:0px;
	top:300px;
	height:200px;
	width:1366px;
	background-color:#902;
}

#main_2{
	position:absolute;
	top:0px;
	left:800px;
	width:566px;
	height:500px;
	background-color:#603;
}
#studentManager{
	position:absolute;
	left:0px;
	top:0px;
	width:266px;
	height:500px;
	background-color:#30C;
}

#personManager{
	position:absolute;
	left:266px;
	top:0px;
	width:300px;
	height:500px;
	background-color:#f70;
}
#tail{
	position:absolute;
	top:700px;
	left:0px;
	width:1366px;
	height:150px;
}
#end{
	position:absolute;
	bottom:0px;
	left:500px;
	width:400px;
	height:20px;
}
	
#h1{
	position:absolute;
	left:500px;
}
</style>
</head>

<script type="text/javascript">
						var r_flag=true;
function loginSure(flag){
	var divid=document.getElementById("register");
	//这个方法正确
	if(r_flag){
		divid.style.display="block";
		r_flag=false;
	}
	else{
		divid.style.display="none";
		r_flag=true;
	}
}
function login(){
     divid.style.display="none";
		r_flag=true;
}
</script> 

<%@ page language="java" import="domain.*" %>
 
<jsp:useBean id="teacher" scope="request" class="domain.TeacherBean" />
 <%-- 
     上面这两段是导入自定义java类，并声明所要使用的类
 --%>
<body>
<div id="head">
<h1 id="h1">
<font size="8" color="#0000FF">成绩管理系统</font></h1>

       <div id="rightUp">
               <!--  <span id="land"> -->
                       
                      <a id="signIn" href="javascript:void(0)" onclick="loginSure(true)"><font size="3"> 登陆</font></a>
                                                                              &nbsp|&nbsp
                    
                        <a href="${pageContext.request.contextPath }/jsp/signUp.jsp">
               <font size="3">
              注册
               </font>
               </a>
                       &nbsp&nbsp&nbsp&nbsp
              <!--    </span> -->
                 
                 
                 
                 <span id="suggest">
                        <font size="3"> 你好 ：
                        
                       <%
                       if(teacher!=null&&teacher.getName()!=null){
                      out.print(teacher.getName()+" "+teacher.getJob());
                       }else {
                       out.print("游客");
                       }
                        %>
                         &nbsp&nbsp&nbsp&nbsp</font>
                 </span>
                 
                 <span id="ourInfo">
                         <font size="3"> 
                         <a id="exitUser" href="${pageContext.request.contextPath }/servlet/Exit"> 退出</a>
                          &nbsp&nbsp&nbsp&nbsp</font>
                  </span> 
                  <br/>
                    <div id="register">
                    <form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
    	学号：<input type="text" name="wageNumber"><br/>
    	密码  ：<input type="password" name="password" /><br/>
    	<input type="submit" value="登陆">
    </form>
                    <br/>
                
                 </div>
                   
       </div>


</div>




<div id="main">

<div id="main_1"> 
     <div id="testManager">
           <h2>
               <a href="${pageContext.request.contextPath }/servlet/TestManagerServlet">
               <font size="5" color="#9933FF">
              考试管理
               </font>
               </a>
           </h2>
     </div>
     
     
     <div id="course">
                 <h2 >  
                          <a href="${pageContext.request.contextPath }/servlet/CourseManagerServlet">
                          <font size="5" color="#0020ff">
                          课程管理:
                          </font>
                          </a>
                 </h2>
           </div>
            
     <div id="news">
          <h2 >  
                <font size="5" color="#c0a7ff">消息通知:</font>
          </h2>
    </div>




<div id="main_2">
     <div id="studentManager">
          <h2 >  
                <a href="${pageContext.request.contextPath }/servlet/StudentManageServlet">
                <font size="5" color="#00c909">
                学生管理
                </font>
                </a>
          </h2>
     </div>
     
      <div id="personManager">
          <h2 >  
                
                <a href="${pageContext.request.contextPath }/servlet/PersonManagerServlet">
                <font size="5" color="#f20900">个人管理</font>
                </a>
                
          </h2>
    </div>
</div>

</div>





<div id="tail">
           <div id="end">
           <span id="joinus">
          作者 ： &nbsp;&nbsp;&nbsp;&nbsp;</span>
           
           <span id="copyRight">&copy;周游&nbsp|&nbsp13130110075 </span>
           
           </div>


 </div>
</body>
</html>
 