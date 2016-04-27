package server;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PersonDao;
import dao.StudentDao;
import domain.StudentBean;
import domain.TeacherBean;

public class SignUpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// if come from Login page, then foward to signUp
		if("fromLogin".equals(req.getParameter("fromLogin"))){
			req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
			return;
		}else{
		try{
		TeacherBean teacher;
		StudentBean student;

		String wageNumber=req.getParameter("wageNumber");
		
		if("".equals(req.getParameter("userName"))||"".equals(req.getParameter("userName").trim())
				||"".equals(wageNumber)||"".equals(wageNumber.trim())
				||"".equals(req.getParameter("job"))||"".equals(req.getParameter("job").trim())
				||"".equals(req.getParameter("password"))||"".equals(req.getParameter("password").trim())
				){
			req.setAttribute("signUp", "请填写完整的信息");
			req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
			return;
		}
		
		
		if(PersonDao.query(wageNumber)!=null||StudentDao.query(wageNumber)!=null){
			req.setAttribute("signUp", "用户已经存在");
			req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
			return;
		}
		if(wageNumber.matches("[0|1|2]([0-9]{10})")){
			student=new StudentBean();
			student.setStu_number(wageNumber);
			student.setName(req.getParameter("userName"));
			student.setClass_number(Integer.parseInt(req.getParameter("classNumber")));
			student.setSex(req.getParameter("gender"));
			student.setStu_type(req.getParameter("studentType"));
			student.setPassword(req.getParameter("password"));
			
			StudentDao.insert(student);
		}else{				
		teacher=new TeacherBean();
		teacher.setWage_number(wageNumber);
		teacher.setPassword(req.getParameter("password"));
		teacher.setName(req.getParameter("userName"));
		teacher.setJob(req.getParameter("job"));
		
		PersonDao.insert(teacher);
		}
		
		req.setAttribute("loginInfo", "注册成功，请登陆");
		req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		
		}catch(SQLException e){
		e.printStackTrace();
		req.setAttribute("signUp", "注册信息不正确,请仔细检查");
	    req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
	}catch(Exception e){
			e.printStackTrace();
			System.out.println("-------------->"+e.getMessage());
			req.setAttribute("errorInfo", "不好意思，注册时粗错鸟~~");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			
		}
		}
		
	}

	/**
	 * 
	 */
	
	
}
