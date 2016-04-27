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

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String wageNumber=request.getParameter("wageNumber");
	       if("".equals(wageNumber)||wageNumber==null||"".equals(wageNumber.trim())){
	    	   request.setAttribute("online", "offline");
	    	   request.setAttribute("loginInfo", "用户名或密码不能为空");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
	       }
			
			 HttpSession session=request.getSession();
			 String sessionID=session.getId();
				Cookie cookie=new Cookie("wageNumber",wageNumber);
				cookie.setPath("/StudentManagerSystem");
				cookie.setMaxAge(1800);					
				response.addCookie(cookie);
				String curUser=" 游客";
			try {
				TeacherBean teacher;
				StudentBean student;
				
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
						    session.setAttribute("online", "online");// mark the current user is online
						    curUser=student.getName();
						   
						  
					
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
					    session.setAttribute("online", "online");// mark the current user is online
					    curUser= teacher.getName()+" "+teacher.getJob();
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}//get the information from databases
			  
			  request.setAttribute("curUser", curUser);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
