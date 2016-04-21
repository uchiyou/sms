package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseQueryDao;
import dao.CourseRecordDao;
import domain.CourseBean;
import domain.CourseRecordBean;
import domain.TeacherBean;

public class CourseManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*//设置一个session用来保存用户id
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		*/
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		
		ArrayList<CourseBean> courseList;
		try {
			courseList = CourseQueryDao.queryTeacherCourses(teacher.getWage_number());
			if((courseList!=null)&&(!courseList.isEmpty())){//if a teacher have no course 
			request.setAttribute("myCourse", courseList);
			
			ArrayList<CourseRecordBean> courseRecord=CourseRecordDao.query(courseList.get(0).getCourse_number());//set the default courseRecord is the first
			request.setAttribute("courseRecord", courseRecord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//get the information from databases
		
		
		request.getRequestDispatcher("/courseManager.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
