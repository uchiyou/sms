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
		
		
		
		// 如果是来自修改的提交按钮，提交的表单中将没有 用户选择的课程号信息. 故需要重新获取用户选取的课程号
	   chooseCourse=request.getParameter("courseNumber2")==null?chooseCourse:Integer.parseInt(request.getParameter("courseNumber2"));
	//   System.out.println("CourseManagerServlet---------->chooseCourse = "+chooseCourse);	
		
		
        // 获取当前教师的所有课程
		ArrayList<CourseRecordBean> allCourseRecord=CourseRecordDao.queryAll();	
		
		
		
		// 找出当前课程记录，
		int maxCourseRecordId=0,maxCurCourseSe=0;
		ArrayList<CourseRecordBean> courseRecord=new ArrayList<CourseRecordBean>();
		for(CourseRecordBean bean:allCourseRecord){
			maxCourseRecordId=bean.getCourse_record_id()>maxCourseRecordId?bean.getCourse_record_id():maxCourseRecordId;
			
			// 找出用于显示用户即将插入记录的序号
			if(bean.getCourse_number()==chooseCourse){//将已选课程的课程记录添加进去
			courseRecord.add(bean);
			maxCurCourseSe=maxCurCourseSe>bean.getSequence()?maxCurCourseSe:bean.getSequence();//获取当前课程最大序列
			}
		}
		
		request.setAttribute("recordSequence", ++maxCurCourseSe);// 设置当前插入的最大记录序列
		request.setAttribute("courseRecord", courseRecord);	
		request.setAttribute("curCourseRecordId", maxCourseRecordId);
		

		
		// 如果用户点击了 插入按钮，则执行插入操作。  放在同一个方法中，是因为他们在同一个界面中，且要关联的参数太多
	//	System.out.println(request.getParameter("orderNumber")+"------>"+request.getParameter("teacherContent"));
		if(request.getParameter("courseRecordHidden")!=null){
			String content=request.getParameter("teacherContent");
			if(content==null||"".equals(content.trim())){
				request.setAttribute("insertCourseRecordInfo", "讲授内容不能为空哦 ！");
				return;
			}
			// 不用检查输入合法性的原因是，不安全的部分是 通过 hidden 标签传递过来的
			CourseRecordBean courseRecordBean=new CourseRecordBean();			
			courseRecordBean.setSequence(Integer.parseInt(request.getParameter("orderNumber")));			
			courseRecordBean.setCourse_content(content);
			courseRecordBean.setType(request.getParameter("courseType"));
            courseRecordBean.setCourse_record_id(1+Integer.parseInt(request.getParameter("curCourseRecordId")));
            courseRecordBean.setCourse_number(Integer.parseInt(request.getParameter("courseNumber2")));
			
            
            // 检查输入和数据库里数据的一致性，防止恶意使用脚本提交数据
			if(check(courseRecordBean, allCourseRecord)){
				request.setAttribute("insertCourseRecordInfo", "插入成功！");
				if(CourseRecordDao.insert(courseRecordBean,teacher)==-1){
					request.setAttribute("insertCourseRecordInfo", "该课程目前没有对应的班级,目前不能插入！！");
					return;
				}
				courseRecord.add(courseRecordBean);
				request.setAttribute("courseRecord", courseRecord);	
				request.setAttribute("recordSequence", ++maxCurCourseSe);// 设置当前插入的最大记录序列
			}else{
				request.setAttribute("insertCourseRecordInfo", "请输入正确的课程记录信息");
			}
			}
		
	}
	
	private boolean check(CourseRecordBean courseRecordBean,ArrayList<CourseRecordBean> allCourseRecord) {
		if(courseRecordBean.getCourse_content()==null||courseRecordBean.getType()==null)
			return false;
		
		for(CourseRecordBean bean:allCourseRecord)// 同一课程，若是数据库中已经存在课程记录序列则错误
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
		if(teacher==null)// 账号异常情况，将会在 Server中发生跳转
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
			request.setAttribute("errorInfo", "courseManagerServlet-->SQLException账号异常错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("errorInfo", "courseManagerServlet-->Exception账号异常错误");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     doPost(request, response);
		}
	// write by uchiyou

}
