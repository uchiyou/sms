<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>

<script type="text/javascript">
function showStudentInfo(radioId){
    var vDiv = document.getElementById('studentInfo');
    vDiv.style.display = (radioId.checked)?'block':'none';
  }
</script>

<center>
       <div id="login">
        <form action="${pageContext.request.contextPath }/servlet/SignUp" method="post">
        <c:choose>
               <c:when test="${signUp=='userExist'}"> 
                         <font color="0x00ff00"> 
                                                                                                                 账号已存在<br/>
                         </font> 
               </c:when>
               <c:when test="${signUp=='unmatch'}">
                      <font color="0x00ff00"> 
                                                                                           输入格式不正确<br/>
                         </font> 
              </c:when>
               <c:when test="${signUp=='none'}">
                      <font color="0x00ff00"> 
                                                                                           请填写完整<br/>
                         </font> 
              </c:when>
             
         </c:choose>
        
        
    	学号 &nbsp ：<input type="text" name="wageNumber"><br/>
    	用户名 ：<input type="text" name="userName"><br/>
    	 密码  &nbsp ：    <input type="password" name="password"><br/>
    	确认 密码：    <input type="password" name="password2"><br/>
    	 身份 &nbsp ：   <input type=radio name="job" value="student" onclick="showStudentInfo(this)" >学生</input>
                   <input type=radio name="job" value="teacher" onclick="showStudentInfo('student')" checked>讲师</input>
                   <input type=radio name="job" value="professor" onclick="showStudentInfo('student')">教授</input>
    	 <br/>
    	 
    	 <div id="studentInfo" style="display:none">
    	      班级 ： <input type="text" name="classNumber"><br/>
    	  性别:<input type="radio" name="gender" value="male"> 男</input>
    	  <input type="radio" name="gender" value="female"> 女</input>
    	  <input type="radio" name="gender" value="others">其他</input><br/>
    	  学生类型：<input type="radio" name="studentType" value="undergraduate">本科生 </input>
    	  <input type="radio" name="studentType" value="postgraduate">研究生 </input><br/>
    	 
    	 </div>
    	 
    	
    	<input type="submit" value="确认注册"/>
    </form>
  </div>
</center>

</body>
</html>