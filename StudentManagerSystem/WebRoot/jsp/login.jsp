<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  write by uchiyou@sina.com, -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>

<style type="text/css">

body{
	margin:0;
	padding:0;
	font-size:12px;
	font-family:"微软雅黑","宋体";
	background:url(../images/background3.jpg) repeat-x;
}


#login{
	position:absolute;
	top:200px;
	left:500px;
	border:#3F0 0px none;
}
#signUpDiv{
    position:relative;
    right:20px;
    bottom: 2px;
}
</style>

<body style="background:url(${pageContext.request.contextPath }/img/cat2.jpg);">
<script type="text/javascript">
function check(node,pattern,behind,tip){
	var text=node.innerText;
	text=text.match(pattern);
	if(text.length<1){
		behind.innerText=tip;
	}else{
		behind.innerText='输入正确';
	}
		
	
}
</script>
<center><!-- ${pageContext.request.contextPath } -->
       <div id="login">
      <font color="0xffffff">${loginInfo}</font><br/>
      
        <form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
    	 <font color="0x999999">
    	用户名：<input type="text"  name="wageNumber" onmousemove="check(this,'\d{3-11}','userNumberTip','输入错误,3-11位数字')" />
    	<font color="0x00ff00"><span id="userNumberTip"></span></font>
    	<br/>
    	 密码：    <input type="password" name="password" onmousemove="check(this,'\w{3-20}','pwdTip','输入格式错误,3-20位字母或数字')" />
    	<font color="0x00ff00"> <span id="pwdTip"></span></font>
    	 <br/>
    	 </font> 
    	<input type="submit" value="登陆"/>
    </form>
    
    <div id="signUpDIv">
    <form action="${pageContext.request.contextPath }/servlet/SignUp" method="post">
         <input type="hidden" name="fromLogin" value="fromLogin"/>
        <input type="submit" value="注册"/>
        </form>
    </div>
 
  
  
<a href="${pageContext.request.contextPath }/index.jsp">
<font color="007777" size='4'>
回到主界面
</font>
</a><br/><br/>
   </div>
</center>
</body>
</html>