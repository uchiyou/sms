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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	//����һ��session���������û�id
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		OutputStream output=response.getOutputStream();
		*/
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		request.setAttribute("teacher", teacher);		
		request.getRequestDispatcher("/personManager.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                doGet(request,response);
	}

}