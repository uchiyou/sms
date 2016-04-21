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
import domain.TeacherBean;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		       String wageNumber=request.getParameter("wageNumber");
				TeacherBean teacher;
				try {
					teacher = PersonDao.query(wageNumber);
					

					//System.out.println("-----------teacher name"+teacher.getName());
					if(teacher==null){
						request.setAttribute("teacherInfo", -1);// -1 mean the wageNumber is not exist
					}else{
						
						request.setAttribute("teacher", teacher);//give the date to jsp
						// keep a session keep teacher wageNumber
						    HttpSession session=request.getSession();
						    session.setAttribute("teacher", teacher);
							String sessionID=session.getId();
							Cookie cookie=new Cookie("SESSION",sessionID);
							cookie.setPath("/StudentManagerSystem");
							cookie.setMaxAge(1800);
							response.addCookie(cookie);
						
						request.setAttribute("teacherInfo", teacher);
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
