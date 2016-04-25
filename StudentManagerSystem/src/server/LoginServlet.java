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
		
		       String wageNumber=request.getParameter("wageNumber");
		       if("".equals(wageNumber)||"".equals(wageNumber.trim())){
		    	   request.setAttribute("online", "offline");
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
					return;
		       }
				
				 HttpSession session=request.getSession();
				 String sessionID=session.getId();
					Cookie cookie=new Cookie("SESSION",sessionID);
					cookie.setPath("/StudentManagerSystem");
					cookie.setMaxAge(1800);					
					response.addCookie(cookie);
				try {
					TeacherBean teacher;
					StudentBean student;
					if(wageNumber.matches("[0|1|2]([0-9]{10})")){// current user is a student
						student=StudentDao.query(wageNumber);
						String password=request.getParameter("password");
						if(password==null||!password.equals(student.getPassword())){
							request.setAttribute("online", "offline");
							request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
							return;
						}

						if(student==null){
							request.setAttribute("studentInfo", -1);// -1 mean the wageNumber is not exist
						}else{
							
							request.setAttribute("student", student);//give the date to jsp
							// keep a session keep teacher wageNumber
							    session.setAttribute("student", student);
							    session.setAttribute("online", "online");// mark the current user is online
							    request.setAttribute("studentInfo", student);
						}
						
					}else{
				
					teacher = PersonDao.query(wageNumber);
					String password=request.getParameter("password");
				
					if(password==null||teacher==null||!password.equals(teacher.getPassword())){
						request.setAttribute("teacher", "-1");
						session.setAttribute("online", "offline");
						request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
						return;
					}else{
						
						request.setAttribute("teacher", teacher);//give the date to jsp
						// keep a session keep teacher wageNumber
						    session.setAttribute("teacher", teacher);
						    session.setAttribute("online", "online");// mark the current user is online
						request.setAttribute("teacherInfo", teacher);
					}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//get the information from databases
	
				request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		doGet(request,response);
	}

}
