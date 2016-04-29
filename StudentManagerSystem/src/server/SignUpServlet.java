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
	// write by uchiyou
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

		String wageNumber=req.getParameter("wageNumber");
		Object bean=Server.checkInput(req, resp);
		//如果用于有非法输入
		if(bean==null){
			req.setAttribute("signUp", "请填写正确的信息");
			req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
			return;
		}
		
		// 检查数据库里是否已经有该注册信息
		if(PersonDao.query(wageNumber)!=null||StudentDao.query(wageNumber)!=null){
			req.setAttribute("signUp", "用户已经存在");
			req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
			return;
		}
		
		//  如果是学生或者老师
		if(wageNumber.matches("[0|1|2]([0-9]{10})")){
			StudentBean student=(StudentBean) bean;
			StudentDao.insert(student);
		}else{				
		TeacherBean teacher=(TeacherBean) bean;
		PersonDao.insert(teacher);
		}
		
		// 
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
