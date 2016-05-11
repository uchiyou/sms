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
import domain.StudentBean;
import domain.TeacherBean;


/*
 *      ����պ����г��ִ� �� Ctrl + F �������� System.out.print ,�ҵ����б�ע�͵�������
 */
public class Server {// write by uchiyou
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
		
		
		// check whether the input is legel
		public static Object checkInput(HttpServletRequest req, HttpServletResponse resp)
				throws Exception{
		
			String userName=req.getParameter("userName");
			String wageNumber=req.getParameter("wageNumber");
			String job=req.getParameter("job");
			String password=req.getParameter("password");
			
			
			if(userName==null||wageNumber==null||job==null||password==null){
			//	System.out.println("get input info found null, in server checkInput method");
				return null;
			}
			
			
			
			
			
			if("".equals(userName)||"".equals(userName.trim())
					||"".equals(job)||"".equals(job.trim())
					||"".equals(wageNumber)||"".equals(wageNumber.trim())
					||"".equals(password)||"".equals(password.trim())/*
					||wageNumber.matches("[0-9]{2,40}")
					||password.matches("([a-z]|[0-9]){2-40}")*/
					){
				//System.out.println("the input have error infomation, in server checkInput method");
				return null;
			}else{
				if(wageNumber.matches("[0|1|2]([0-9]{10})")){
					StudentBean student=new StudentBean();
					
					student.setStu_number(wageNumber);
					student.setName(userName);
					student.setClass_number(Integer.parseInt(req.getParameter("classNumber")));
					student.setSex(req.getParameter("gender"));
					student.setStu_type(req.getParameter("studentType"));
					student.setPassword(password);
					return student;
				}else{
					TeacherBean teacher=new TeacherBean();
					teacher.setWage_number(wageNumber);
					teacher.setName(userName);
					teacher.setJob(job);
					teacher.setPassword(password);
					return teacher;
				}
				
			}
		
		}
		
		
		
		
		// check if the current request is online
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		
		HttpSession session=request.getSession();
		String online= (String) session.getAttribute("online");
	//	System.out.println("----------->"+online);
		if(!"online".equals(online)){
		//	System.out.println("in server.checkLogin----> online is not online");
			request.setAttribute("online", "offline");
			request.setAttribute("loginInfo", "�������ߣ�����µ�½");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
	
	
	
	
	// query teacher's course��Save his all course information into request and return current user choice course id
	public static Integer getTeacherCourse(HttpServletRequest request, HttpServletResponse response,TeacherBean teacher)
			throws ServletException, IOException, SQLException{
		/*
		 * getTeacherCourse will set current teacher's course into HttpServletRequest as named courseList
		 * and will return the course which user had choice
		 * */
		// if user choose a course, then set the courseId
		String courseId=request.getParameter("chooseCourse");
		Integer chooseCourse=courseId==null?-1:Integer.parseInt(courseId);
		
		//System.out.println("chooseCouse --------------->"+chooseCourse);
		
		
		
		//  ��ȡ��ʦ�����пγ�
		LinkedList<CourseBean> courseList=new LinkedList<CourseBean>();// so courseList can't be null
		
/*			courseList.addAll(((List<CourseBean>)(CourseQueryDao.queryTeacherCourses(teacher.getWage_number()))));*/
		  ArrayList<CourseBean>  list=  CourseQueryDao.queryTeacherCourses(teacher.getWage_number());
		    if(list!=null)
			courseList.addAll(list);
		    else{
		    	request.setAttribute("noneCourseInfo", "��û�н��κογ�");
		    	return -1;
		    }
			
		
		    
			//  ����ѯ���� teacherCourse ������浽 request��
			if(!courseList.isEmpty()){//if a teacher have no course 					
			chooseCourse=chooseCourse==-1?courseList.get(0).getCourse_number():chooseCourse;
			setCourseListOrder(courseList, chooseCourse);
			request.setAttribute("curCourse", chooseCourse);
			request.setAttribute("courseList", courseList);	
			request.setAttribute("curCourseName", CourseQueryDao.query(chooseCourse).getCourse_name());
			}
			return chooseCourse;

	}
	
	
	
	
	
	// if return null, which mean had forward in getTeacher,
	public static TeacherBean getTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		
		
		
		if(teacher==null){
			
			// ��� session ��û�У����Դ� Cookie ��ȡ
			Cookie[] cookies=request.getCookies();
			String wageNumber=null;
			if(cookies!=null){
			for(int i=0;i<cookies.length;++i){
				if(cookies[i].getName().equals("wageNumber")){
					wageNumber=cookies[i].getValue();
					teacher=PersonDao.query(wageNumber);
					if(teacher!=null)
					session.setAttribute("teacher", teacher);
					break;
				}
			}
			}else{//���û��Cookie��˵���ǵ�һ�ε�½
			//	System.out.println("server.getTeacher()---->cookie is null");
				request.setAttribute("loginInfo", "���ȵ�¼");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return null;
			}
			
		
			
		}
		
		
		return teacher;		
	}

	
	
}
