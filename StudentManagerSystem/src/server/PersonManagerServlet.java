package server;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PersonDao;
import domain.TeacherBean;

public class PersonManagerServlet extends HttpServlet {

	public void setPersonInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		TeacherBean teacher=Server.getTeacher(request, response);
		request.setAttribute("wageNumber", teacher.getWage_number());
		request.setAttribute("userName", teacher.getName());
		request.setAttribute("job", teacher.getJob());
		
		String modify="readonly='readonly'";
		request.setAttribute("modify", modify);
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		try {
			if(!Server.checkLogin(request, response)) 
				return;
			setPersonInfo(request, response);
			
			request.getRequestDispatcher("/personManager.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "设置个人信息时不知道为什么出错了");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
			
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                doGet(request,response);
	}

}
