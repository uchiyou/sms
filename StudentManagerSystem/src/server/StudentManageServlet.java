package server;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseQueryDao;
import dao.CourseRecordDao;
import dao.PersonDao;
import dao.StudentDao;
import dao.StudentScoreQueryDao;
import domain.CourseBean;
import domain.CourseRecordBean;
import domain.StudentBean;
import domain.StudentCourseScoreBean;
import domain.TeacherBean;

public class StudentManageServlet extends HttpServlet {

	private void dealTopPart(HttpServletRequest request, HttpServletResponse response,String smpart)
			throws ServletException, IOException, SQLException {
					request.setAttribute("displayStuInfo", "none");
					if(smpart!=null){
					if(smpart.equals("1")){
						String studentNumber=request.getParameter("studentNumber1");
						if(studentNumber==null||"".equals(studentNumber)){
							request.setAttribute("studentNumber1Info", "输入不能为空");
						}else{
						StudentBean studentBean=StudentDao.query(studentNumber);
						if(studentBean==null){
							request.setAttribute("studentNumber1Info", "不存在此学号的学生");
						}else{
						request.setAttribute("studentBean", studentBean);
						request.setAttribute("displayStuInfo", "block");
						}
						}
					}}    		
	}
	
	private void dealBottomPart(HttpServletRequest request, HttpServletResponse response,String smpart)
			throws ServletException, IOException, SQLException {
		int classNumber=-1;
		int choiceCourse=-1;
		
		if(smpart!=null)
		if(smpart.equals("2")){
			if(request.getParameter("ClassNumber")==null||"".equals(request.getParameter("ClassNumber"))){
				
			}else{
				classNumber=Integer.parseInt(request.getParameter("ClassNumber"));	
			}
			
			choiceCourse=Integer.parseInt(request.getParameter("chooseCourse"));
		}
		
		
	// the follow table
	ArrayList<StudentCourseScoreBean> scoreList = new ArrayList<StudentCourseScoreBean>();
	ArrayList<CourseBean> courseList;
	    TeacherBean teacher=Server.getTeacher(request, response);
	    if(teacher==null)// 账号异常情况，将会在 Server中发生跳转
	    	return;
		
		courseList = CourseQueryDao.queryTeacherCourses(teacher.getWage_number());
		request.setAttribute("courseList", courseList);
		CourseBean course=choiceCourse==-1?courseList.get(0):CourseQueryDao.query(choiceCourse);
		
		request.setAttribute("defaultCourse", course);
		ArrayList<Integer> myclass=PersonDao.getClass(teacher.getWage_number());
		Iterator<Integer> it=myclass.iterator();
		if(classNumber!=-1){
			ArrayList<StudentCourseScoreBean> studentSocre=StudentScoreQueryDao.query(course.getCourse_number(), classNumber);
			if(studentSocre!=null)
			scoreList.addAll(studentSocre);
			else
			request.setAttribute("classInfo", "不存在与该课程对应的班级");
		}else{
		while(it.hasNext()){		
			scoreList.addAll(StudentScoreQueryDao.query(course.getCourse_number(), it.next()));	
		
		}}
		
		
		if(scoreList!=null&&scoreList.size()>0){
			request.setAttribute("scoreList", scoreList);
		}
	}
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!Server.checkLogin(request, response)) 
			return;
		
		try {
			String smpart=request.getParameter("smpart");
			dealTopPart(request, response, smpart);
			dealBottomPart(request, response, smpart);
		    request.getRequestDispatcher("/studentManager.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "StudentManagerServlet-->账号异常错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
