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
import dao.TestManagerUtil;
import domain.CourseBean;
import domain.DetailQuestionScoreBean;
import domain.KnowledgeDistributeBean;
import domain.ScoreRangeBean;
import domain.TeacherBean;

public class TestManagerServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6685319235932325771L;
	public void dealKnowledgeDistributeTable1(HttpServletRequest request, HttpServletResponse response,CourseBean course)
			throws ServletException, IOException, SQLException {
		//get the all info
		ArrayList<KnowledgeDistributeBean> distribute;
		
		distribute=TestManagerUtil.queryKnowledgeDistribute(course.getCourse_number());
		request.setAttribute("distributeList", distribute);
		
	}
	public void dealQuestionScoreAverageTable2(HttpServletRequest request, HttpServletResponse response,ArrayList<DetailQuestionScoreBean> questionQuestionList)
			throws ServletException, IOException {
		
	}
	public void dealScoreSpanAverageTable3(HttpServletRequest request, HttpServletResponse response,ArrayList<ScoreRangeBean> scoreList)
			throws ServletException, IOException {
		int aa=0,ba=0,ca=0,da=0;
		
		if(scoreList!=null)
		for(ScoreRangeBean bean:scoreList){
		System.out.println("scoreList :"+bean.getA()+"--"+bean.getClass_number()+"--"+bean.getCourse_record_id());
		aa+=bean.getA();
		ba+=bean.getB();
		ca+=bean.getC();
		da+=bean.getD();		
		}
		
		request.setAttribute("aa", aa);
		request.setAttribute("ba", ba);
		request.setAttribute("ca", ca);
		request.setAttribute("da", da);
		request.setAttribute("scoreList", scoreList);
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				
				try {
					// ------->use util class Server
					if(!Server.checkLogin(request, response)) 
						return;
					TeacherBean teacher=Server.getTeacher(request, response);
					if(teacher==null)// 账号异常情况，将会在 Server中发生跳转
				    	return;					
			        Integer chooseCourse= Server.getTeacherCourse(request, response, teacher);
			        
			        // ----------->prepare  the necessary data for dealXXXTable method
					CourseBean course=CourseQueryDao.query(chooseCourse);
					request.setAttribute("defaultCourse", course);

					
					ArrayList<ScoreRangeBean> scoreList=TestManagerUtil.queryScoreRangeBean(teacher.getWage_number(),course.getCourse_number());
			        
					
			      //  ArrayList<DetailQuestionScoreBean> questionQuestionList=TestManagerUtil.queryDetailQuestionScore(course.getCourse_number());

					// ------------>use util method like deadXXXTable
					dealKnowledgeDistributeTable1(request, response, course);
					//dealQuestionScoreAverageTable2(request, response, questionQuestionList);
					dealScoreSpanAverageTable3(request, response, scoreList);
					request.getRequestDispatcher("/testManager.jsp").forward(request, response);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					request.setAttribute("errorInfo", "testManager 不知道在什么地方出错了");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}//get the information from databases
				
				

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
