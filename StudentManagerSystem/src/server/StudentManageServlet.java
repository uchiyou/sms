package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseQueryDao;
import dao.CourseRecordDao;
import dao.PersonDao;
import dao.StudentScoreQueryDao;
import domain.CourseBean;
import domain.CourseRecordBean;
import domain.StudentCourseScoreBean;
import domain.TeacherBean;

public class StudentManageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!Server.checkLogin(request, response)) 
			return;
		
		
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
	    		
		ArrayList<StudentCourseScoreBean> scoreList = new ArrayList<StudentCourseScoreBean>();
		ArrayList<CourseBean> courseList;
		try {
			
			
			
			courseList = CourseQueryDao.queryTeacherCourses(teacher.getWage_number());
			CourseBean course=courseList.get(0);
			request.setAttribute("defaultCourse", course);
			ArrayList<Integer> myclass=PersonDao.getClass(teacher.getWage_number());
			Iterator<Integer> it=myclass.iterator();
			while(it.hasNext()){
				Integer class_number=it.next();
				scoreList.addAll(StudentScoreQueryDao.query(course.getCourse_number(), class_number));
			}
			
			
			if(scoreList!=null&&scoreList.size()>0){
				request.setAttribute("scoreList", scoreList);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//get the information from databases
		
		
		request.getRequestDispatcher("/studentManager.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
