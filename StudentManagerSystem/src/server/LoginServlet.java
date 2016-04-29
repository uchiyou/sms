package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PersonDao;
import dao.StudentDao;
import domain.StudentBean;
import domain.TeacherBean;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5133019558550915900L;


	// write by uchiyou
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// check input
		String wageNumber=request.getParameter("wageNumber");
	       if("".equals(wageNumber)||wageNumber==null||"".equals(wageNumber.trim())){
	    	   request.setAttribute("loginInfo", "用户名或密码不能为空");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
	       }else if(!wageNumber.matches("[0-9]{3,}")){
    		   request.setAttribute("loginInfo", "学号或工资号格式不正确");
    		   request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
	       }
			
	       
	       // set session 
			 HttpSession session=request.getSession();
			 /*session.setAttribute("teacher", teacher);
			 session.setAttribute("online", "online");// mark the current user is online
			 */
			 session.removeAttribute("curUser");
			 session.removeAttribute("online");
			 session.removeAttribute("teacher");			
			 session.removeAttribute("student");
			 
			 session.setMaxInactiveInterval(1800);
			// String sessionID=session.getId();
			 Cookie[] cookies=request.getCookies();
			 if(cookies==null){// 如果没有 Cookie ，则创建一个 Cookie,
				Cookie cookie=new Cookie("wageNumber",wageNumber);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(3600*24);					
				response.addCookie(cookie);
			 }else{// 如果有，则修改Cookie的值
				 for(Cookie cook:cookies){
					 if(cook.getName().equals("wageNumber")){
						 cook.setValue(wageNumber);
						 break;
					 }
				 }
			 }
				
				
				
				// set user infomation 
				String curUser=" 游客";
				
			try {
				TeacherBean teacher;
				StudentBean student;
				
				
				// this case is a student
				if(wageNumber.matches("[0|1|2]([0-9]{10})")){// current user is a student
					student=StudentDao.query(wageNumber);
					String password=request.getParameter("password");
					
					if(password==null||student==null||!password.equals(student.getPassword())){
						request.setAttribute("online", "offline");
						request.setAttribute("loginInfo", "用户名或密码错误");
						request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
						return;
					}
						request.setAttribute("student", student);//give the date to jsp
						// keep a session keep teacher wageNumber
						    session.setAttribute("student", student);						   
						    curUser=student.getName();
						   
						  
					// this case is a teacher
				}else{
			
				teacher = PersonDao.query(wageNumber);
				String password=request.getParameter("password");
			
				if(password==null||teacher==null||!password.equals(teacher.getPassword())){
					request.setAttribute("teacher", "-1");
					session.setAttribute("online", "offline");
					request.setAttribute("loginInfo", "用户名或密码不正确");
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
					return;
				}else{
					
					   request.setAttribute("teacher", teacher);//give the date to jsp
					// keep a session keep teacher wageNumber
					    session.setAttribute("teacher", teacher);					   
					    curUser= teacher.getName()+" "+teacher.getJob();
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorInfo", "登陆时，出现了 SQL Exception");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}//get the information from databases
			  
			session.setAttribute("online", "online");// mark the current user is online
			session.setAttribute("curUser", curUser);
			request.removeAttribute("curUser");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
