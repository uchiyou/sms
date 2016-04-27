package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		     doPost(request, response);
		}
	

	private void showCourseRecord(HttpServletRequest request, HttpServletResponse response,Integer chooseCourse)
			throws ServletException, IOException, SQLException{

		ArrayList<CourseRecordBean> allCourseRecord=CourseRecordDao.queryAll();	
		
		
		int maxCourseRecordId=0,maxCurCourseSe=0;
		ArrayList<CourseRecordBean> courseRecord=new ArrayList<CourseRecordBean>();
		for(CourseRecordBean bean:allCourseRecord){
			maxCourseRecordId=bean.getCourse_record_id()>maxCourseRecordId?bean.getCourse_record_id():maxCourseRecordId;
			
			
			if(bean.getCourse_number()==chooseCourse){//����ѡ�γ̵Ŀγ̼�¼��ӽ�ȥ
			courseRecord.add(bean);
			maxCurCourseSe=maxCurCourseSe>bean.getSequence()?maxCurCourseSe:bean.getSequence();//��ȡ��ǰ�γ��������
			
			}
		}
		
		request.setAttribute("recordSequence", ++maxCurCourseSe);// ���õ�ǰ���������¼����
		request.setAttribute("courseRecord", courseRecord);	
		request.setAttribute("curCourseRecordId", maxCourseRecordId);
		

		System.out.println(request.getParameter("orderNumber")+"------>"+request.getParameter("teacherContent"));
		if(request.getParameter("courseRecordHidden")!=null){
			CourseRecordBean courseRecordBean=new CourseRecordBean();			
			courseRecordBean.setSequence(Integer.parseInt(request.getParameter("orderNumber")));
			
			courseRecordBean.setCourse_content(request.getParameter("teacherContent"));
			courseRecordBean.setType(request.getParameter("courseType"));
            courseRecordBean.setCourse_record_id(1+Integer.parseInt(request.getParameter("curCourseRecordId")));
            courseRecordBean.setCourse_number(Integer.parseInt(request.getParameter("courseNumber2")));
			
			if(check(courseRecordBean, allCourseRecord)){
				request.setAttribute("insertCourseRecordInfo", "����ɹ���");
				CourseRecordDao.insert(courseRecordBean);
				courseRecord.add(courseRecordBean);
				request.setAttribute("courseRecord", courseRecord);	
				request.setAttribute("recordSequence", ++maxCurCourseSe);// ���õ�ǰ���������¼����
			}else{
				request.setAttribute("insertCourseRecordInfo", "��������ȷ�Ŀγ̼�¼��Ϣ");
			}
			}
		
	}
	
	private boolean check(CourseRecordBean courseRecordBean,ArrayList<CourseRecordBean> allCourseRecord) {
		if(courseRecordBean.getCourse_content()==null||courseRecordBean.getType()==null)
			return false;
		
		for(CourseRecordBean bean:allCourseRecord)// ͬһ�γ̣��������ݿ����Ѿ����ڿγ̼�¼���������
			if(bean.getSequence()==courseRecordBean.getSequence()&&bean.getCourse_number()==courseRecordBean.getCourse_number())
				return false;
		return true;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		if(!Server.checkLogin(request, response)) 
			return;
		TeacherBean teacher=Server.getTeacher(request, response);
		if(teacher==null)// �˺��쳣����������� Server�з�����ת
	    	return;
		
        Integer chooseCourse= Server.getTeacherCourse(request, response, teacher);

        showCourseRecord(request, response, chooseCourse);
        
		request.getRequestDispatcher("/courseManager.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "courseManagerServlet-->�˺��쳣����");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}//get the information from databases
		
	}

}
