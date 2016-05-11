package server;// write by uchiyou

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

	
	

	private void showCourseRecord(HttpServletRequest request, HttpServletResponse response,Integer chooseCourse, TeacherBean teacher)
			throws ServletException, IOException, SQLException{
		
		
		
		// ����������޸ĵ��ύ��ť���ύ�ı��н�û�� �û�ѡ��Ŀγ̺���Ϣ. ����Ҫ���»�ȡ�û�ѡȡ�Ŀγ̺�
	   chooseCourse=request.getParameter("courseNumber2")==null?chooseCourse:Integer.parseInt(request.getParameter("courseNumber2"));
	//   System.out.println("CourseManagerServlet---------->chooseCourse = "+chooseCourse);	
		
		
        // ��ȡ��ǰ��ʦ�����пγ�
		ArrayList<CourseRecordBean> allCourseRecord=CourseRecordDao.queryAll();	
		
		
		
		// �ҳ���ǰ�γ̼�¼��
		int maxCourseRecordId=0,maxCurCourseSe=0;
		ArrayList<CourseRecordBean> courseRecord=new ArrayList<CourseRecordBean>();
		for(CourseRecordBean bean:allCourseRecord){
			maxCourseRecordId=bean.getCourse_record_id()>maxCourseRecordId?bean.getCourse_record_id():maxCourseRecordId;
			
			// �ҳ�������ʾ�û����������¼�����
			if(bean.getCourse_number()==chooseCourse){//����ѡ�γ̵Ŀγ̼�¼��ӽ�ȥ
			courseRecord.add(bean);
			maxCurCourseSe=maxCurCourseSe>bean.getSequence()?maxCurCourseSe:bean.getSequence();//��ȡ��ǰ�γ��������
			}
		}
		
		request.setAttribute("recordSequence", ++maxCurCourseSe);// ���õ�ǰ���������¼����
		request.setAttribute("courseRecord", courseRecord);	
		request.setAttribute("curCourseRecordId", maxCourseRecordId);
		

		
		// ����û������ ���밴ť����ִ�в��������  ����ͬһ�������У�����Ϊ������ͬһ�������У���Ҫ�����Ĳ���̫��
	//	System.out.println(request.getParameter("orderNumber")+"------>"+request.getParameter("teacherContent"));
		if(request.getParameter("courseRecordHidden")!=null){
			String content=request.getParameter("teacherContent");
			if(content==null||"".equals(content.trim())){
				request.setAttribute("insertCourseRecordInfo", "�������ݲ���Ϊ��Ŷ ��");
				return;
			}
			// ���ü������Ϸ��Ե�ԭ���ǣ�����ȫ�Ĳ����� ͨ�� hidden ��ǩ���ݹ�����
			CourseRecordBean courseRecordBean=new CourseRecordBean();			
			courseRecordBean.setSequence(Integer.parseInt(request.getParameter("orderNumber")));			
			courseRecordBean.setCourse_content(content);
			courseRecordBean.setType(request.getParameter("courseType"));
            courseRecordBean.setCourse_record_id(1+Integer.parseInt(request.getParameter("curCourseRecordId")));
            courseRecordBean.setCourse_number(Integer.parseInt(request.getParameter("courseNumber2")));
			
            
            // �����������ݿ������ݵ�һ���ԣ���ֹ����ʹ�ýű��ύ����
			if(check(courseRecordBean, allCourseRecord)){
				request.setAttribute("insertCourseRecordInfo", "����ɹ���");
				if(CourseRecordDao.insert(courseRecordBean,teacher)==-1){
					request.setAttribute("insertCourseRecordInfo", "�ÿγ�Ŀǰû�ж�Ӧ�İ༶,Ŀǰ���ܲ��룡��");
					return;
				}
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
		
		// courseList will be set into request by Server.getTeacherCourse
        Integer chooseCourse= Server.getTeacherCourse(request, response, teacher);
     //   System.out.println("CourseManagerServlet---------->chooseCourse = "+chooseCourse);
        if(chooseCourse==-1){
        	request.getRequestDispatcher("/courseManager.jsp").forward(request, response);
        	return;
        }

        // show and deal the insert data into database
        showCourseRecord(request, response, chooseCourse,teacher);
        
		request.getRequestDispatcher("/courseManager.jsp").forward(request, response);
		
		
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "courseManagerServlet-->SQLException�˺��쳣����");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("errorInfo", "courseManagerServlet-->Exception�˺��쳣����");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     doPost(request, response);
		}
	// write by uchiyou

}
