package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseQueryDao;
import dao.PersonDao;
import dao.StudentDao;
import dao.StudentScoreQueryDao;
import domain.CourseBean;
import domain.StudentBean;
import domain.StudentCourseScoreBean;
import domain.TeacherBean;

public class StudentManageServlet extends HttpServlet {
	// write by uchiyou
	

	private void dealTopPart(HttpServletRequest request, HttpServletResponse response,String smpart)
			throws ServletException, IOException, SQLException {
					request.setAttribute("displayStuInfo", "none");
					if(smpart!=null){
					if(smpart.equals("1")){
						String studentNumber=request.getParameter("studentNumber1");
						if(studentNumber==null||"".equals(studentNumber)){
							request.setAttribute("studentNumber1Info", "���벻��Ϊ��");
						}else{
						StudentBean studentBean=StudentDao.query(studentNumber);
						if(studentBean==null){
							request.setAttribute("studentNumber1Info", "�����ڴ�ѧ�ŵ�ѧ��");
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
		int chooseCourse=-1;
		
		if(smpart!=null)
		if(smpart.equals("2")){
			String classNUmberStr=request.getParameter("ClassNumber");
			if(classNUmberStr==null||"".equals(classNUmberStr)){
				
			}else{
				if(classNUmberStr.matches("[0-9]{5}"))
				classNumber=Integer.parseInt(request.getParameter("ClassNumber"));
				else
				request.setAttribute("classInfo", "�༶�Ÿ�ʽ���벻��ȷ");
				
			}
			
			chooseCourse=Integer.parseInt(request.getParameter("chooseCourse"));
		}	
		
	// the follow table
	ArrayList<StudentCourseScoreBean> scoreList = new ArrayList<StudentCourseScoreBean>();
	LinkedList<CourseBean> courseList=new LinkedList<CourseBean>();
	
	    TeacherBean teacher=Server.getTeacher(request, response);
	    if(teacher==null)// �˺��쳣����������� Server�з�����ת
	    	return;
		
	    ArrayList<CourseBean>  list=  CourseQueryDao.queryTeacherCourses(teacher.getWage_number());
	    if(list!=null)
		courseList.addAll(list);
	    else{
	    	request.setAttribute("noneCourseInfo", "��û�н��κογ�");
	    	return;
	    }
		CourseBean course=chooseCourse==-1?courseList.get(0):CourseQueryDao.query(chooseCourse);
		
		Server.setCourseListOrder(courseList, chooseCourse);
		request.setAttribute("courseList", courseList);
		request.setAttribute("defaultCourse", course);
		
		ArrayList<Integer> myclass=PersonDao.getClass(teacher.getWage_number());		
		if(classNumber!=-1){
			ArrayList<StudentCourseScoreBean> studentSocre=StudentScoreQueryDao.query(course.getCourse_number(), classNumber);
			if(studentSocre!=null)
			scoreList.addAll(studentSocre);
			else
			request.setAttribute("classInfo", "��������ÿγ̶�Ӧ�İ༶");
		}else{
		Iterator<Integer> it=myclass.iterator();
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
			request.setAttribute("errorInfo", "StudentManagerServlet-->�˺��쳣����(SQLException");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("errorInfo", "StudentManagerServlet-->�˺��쳣����(Exception");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	
		
	}

	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
