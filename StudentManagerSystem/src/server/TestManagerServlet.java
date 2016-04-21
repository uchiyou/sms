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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
				HttpSession session=request.getSession();
				TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
				
				
				//get the all info
				ArrayList<KnowledgeDistributeBean> distribute;
				try {
					
					
					
					
					CourseBean course=CourseQueryDao.queryTeacherCourses(teacher.getWage_number()).get(0);
					request.setAttribute("defaultCourse", course);

					distribute=TestManagerUtil.queryKnowledgeDistribute(course.getCourse_number());
					request.setAttribute("distributeList", distribute);
					
					
					
					ArrayList<ScoreRangeBean> scoreList=TestManagerUtil.queryScoreRangeBean(teacher.getWage_number(),course.getCourse_number());
			        request.setAttribute("scoreList", scoreList);
					
			    //  ArrayList<DetailQuestionScoreBean> questionQuestionList=TestManagerUtil.queryDetailQuestionScore(course.getCourse_number());
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//get the information from databases
				
				
				request.getRequestDispatcher("/testManager.jsp").forward(request, response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
