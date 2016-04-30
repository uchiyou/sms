<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!--  write by uchiyou@sina.com, -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.wholeTable{
  left:15cm;
  top: 10cm;
  
}
</style>
</head>


<body style="background:url(${pageContext.request.contextPath }/img2/water2.jpg);">

<script type="text/javascript">
function showStudentInfo(radioId){
    var vDiv1 = document.getElementById('studentInfo1');
    vDiv1.style.display = (radioId.checked)?'block':'none';
    
    var vDiv2 = document.getElementById('studentInfo2');
    vDiv2.style.display = (radioId.checked)?'block':'none';
    
    var vDiv3 = document.getElementById('studentInfo3');
    vDiv3.style.display = (radioId.checked)?'block':'none';
    
   
  }
</script>

<center>
       <div id="login" class="wholeTable">
        <form action="${pageContext.request.contextPath }/servlet/SignUp" method="post">
        
        <table class="wholeTable">
        <tr>
        <td>
                 <font color="0x008800"> ${signUp}</font>
        </td>
        </tr>
        <tr>
        　　　　　　　　<td>
    	                                  学号 
    	    </td>
    	    <td>
                 <input type="text" name="wageNumber" value="${wageNumber}"/>
    	    </td>
    	</tr>
    	<tr>
    	<td>
    	用户名 
    	</td>
    	<td>
    	<input type="text" name="userName" value="${userName}">
        </td>
    	</tr>
    	<tr>
    	<td>
    	 密码  
    	 </td>
    	 <td>   
    	  <input type="password" name="password" value="${password }">
         </td>
         </tr>
         <tr>
         <td>
    	确认 密码
    	</td>
    	<td>
    	  <input type="password" name="password2" value="${password2 }">
    	  </td>
    	  </tr>
       <tr>
         <td>
    	                身份 
    	 </td>
    	 <td width="1">
               <input type=radio name="job" value="student" onclick="showStudentInfo(this)"/>学生
         </td>
         <td width="200">
              <input type=radio name="job" value="teacher" onclick="showStudentInfo('student')" checked/>讲师
         </td>
         <td>
              <input type=radio name="job" value="professor" onclick="showStudentInfo('student')"/>教授
    	 </td>
    	</tr>
    
    		
    	 <tr id="studentInfo1" style="display:none">
    	 <td>
    	      班级 
    	      </td>
    	      <td>
    	      <input type="text" name="classNumber">
              </td>
              </tr>
        <tr id="studentInfo2" style="display:none">
              <td>
    	  性别
    	  </td>
    	 <td>
    	  <input type="radio" name="gender" value="male"/> 男
    	  </td>
    	  <td>
    	 
    	  <input type="radio" name="gender" value="female"/> 女
    	  </td>
    	  <td>
    	  <input type="radio" name="gender" value="others"/>其他
    	  </td>
    	  </tr>
    	  <tr id="studentInfo3" style="display:none">
    	  <td>
    	  学生类型
    	  </td>
    	  <td>
    	  
    	  <input type="radio" name="studentType" value="undergraduate"/>本科生 
    	  </td>
    	  <td>
    	  
    	  <input type="radio" name="studentType" value="postgraduate"/>研究生 
    	  </td>
    	 </tr>
    	
    	<tr>
    	<td></td>
    	<td>
    	<input type="submit" value="确认注册"/>
    	</td></tr>
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