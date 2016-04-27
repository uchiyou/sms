package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseQueryDao;
import dao.PersonDao;
import domain.CourseBean;
import domain.TeacherBean;

public class Server {
	//-------------------------mainly util for inner server method ,
		public static void setCourseListOrder(LinkedList<CourseBean> courseList,Integer chooseCourse){
			int i=courseList.size();
			for(--i;i>=0;--i){
				if(chooseCourse==courseList.get(i).getCourse_number()){
					courseList.push(courseList.remove(i));
					return;
				}
			}
			
		}
	
		
		
		
	//-----------------------server method for server
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session=request.getSession();
		String online= (String) session.getAttribute("online");
		if(!"online".equals(online)){
			request.setAttribute("online", "offline");
			request.setAttribute("loginInfo", "你已下线，请从新登陆");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
	public static Integer getTeacherCourse(HttpServletRequest request, HttpServletResponse response,TeacherBean teacher)
			throws ServletException, IOException, SQLException{
		/*
		 * getTeacherCourse will set current teacher's course into HttpServletRequest as named courseList
		 * and will return the course which user had choice
		 * */
		
		String courseId=request.getParameter("chooseCourse");
		Integer chooseCourse=request.getParameter("chooseCourseRecord")==null?-1:Integer.parseInt(courseId==null?"-1":courseId);
		
		//System.out.println("chooseCouse --------------->"+chooseCourse);
		
		
		
		
		LinkedList<CourseBean> courseList=new LinkedList<CourseBean>();// so courseList can't be null
		
			courseList.addAll(((List<CourseBean>)(CourseQueryDao.queryTeacherCourses(teacher.getWage_number()))));
			
			
			request.setAttribute("curUser", teacher.getName()+" "+teacher.getJob());
			
			
			if(!courseList.isEmpty()){//if a teacher have no course 					
			chooseCourse=chooseCourse==-1?courseList.get(0).getCourse_number():chooseCourse;
			setCourseListOrder(courseList, chooseCourse);
			request.setAttribute("curCourse", chooseCourse);
			request.setAttribute("courseList", courseList);	
			request.setAttribute("curCourseName", CourseQueryDao.query(chooseCourse).getCourse_name());
			}
			return chooseCourse;

	}
	
	
	public static TeacherBean getTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		if(teacher==null){
			Cookie[] cookies=request.getCookies();
			
			String wageNumber=null;
			for(int i=0;i<cookies.length;++i){
				if(cookies[i].getName().equals("wageNumber")){
					wageNumber=cookies[i].getValue();
					//session.setAttribute("teacher", teacher);
					break;
				}
			}
			
			if(wageNumber!=null){
					teacher= PersonDao.query(wageNumber);
					if(teacher==null){
						request.setAttribute("loginInfo", "账号异常，请从新登陆");
						request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
						return null;
					}else{
						session.setAttribute("teacher", teacher);
					}
			}
		}else{
			// should do something here ....
		}
		
		return teacher;		
	}

	
	
}
