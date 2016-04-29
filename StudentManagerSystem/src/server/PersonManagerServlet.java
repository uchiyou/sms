package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDao;
import domain.TeacherBean;

public class PersonManagerServlet extends HttpServlet {
	// write by uchiyou
	//设置信息给 jsp
	private void setInfo(HttpServletRequest request,TeacherBean teacher,boolean modify)
			throws Exception {
		if(teacher==null) return;
		request.setAttribute("wageNumber", teacher.getWage_number());
		request.setAttribute("userName", teacher.getName());
		request.setAttribute("password", teacher.getPassword());
		List<String> jobs=new LinkedList<String>();
		if(modify){
			jobs.add("teacher");
			jobs.add("professor");
			jobs.add("assiocator professor");
			jobs.remove(teacher.getJob());
			jobs.add(0, teacher.getJob());
			
		}else{
			jobs.add(teacher.getJob());
		}
		request.setAttribute("jobs", jobs);
	}
	
	// 处理用户点击修改信息按钮
	private boolean updatePersonInfo(HttpServletRequest request, HttpServletResponse response,TeacherBean teacher)
			throws Exception {

		// 如果用户点击了修改按钮的处理
		String modify="readonly='readonly'";
		request.setAttribute("modifyCommit", "修改");
		if("userModify".equals(request.getParameter("userModify"))){
			
       //     Object bean=Server.checkInput(request, response);
			String userName=request.getParameter("userName");
			String job=request.getParameter("job");
			String password=request.getParameter("password");
			System.out.println("-uu-------->"+userName+"--"+job+"---"+password);
			if(userName==null||"".equals(userName.trim())
				||password==null&&"".equals(password.trim())){
				request.setAttribute("modifyErrorInfo", "请输入正确的格式");
				return true;
			}else{
				if(teacher.getJob().equals(job)&&teacher.getName().equals(userName)&&teacher.getPassword().equals(password)){
					setInfo(request, teacher,true);
					request.setAttribute("modifyCommit", "确认修改");
					return true;
				}
				
				teacher.setJob(job);
				teacher.setName(userName);
				teacher.setPassword(password);
				PersonDao.update(teacher);
				//有可能修改密码，需要重新登陆
				request.setAttribute("loginInfo", "修改成功,请从新登陆");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return false;
			}
			
          /*  
			if(bean!=null){//如果输入信息正确
				teacher=(TeacherBean) bean;
			    PersonDao.update(teacher);
				request.setAttribute("modify", modify);
			}else{
				request.setAttribute("modifyErrorInfo", "请输入正确的格式");
				return true;
			}
			*/
			
		
		}else{
			request.setAttribute("modify", modify);
			return true;
		}
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
			
			setInfo(request, teacher,false);
			if(!updatePersonInfo(request, response,teacher))//如果已经修改并且跳转了，
				return;
			
			request.getRequestDispatcher("/personManager.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "设置个人信息时不知道为什么出错了(SQLException)");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorInfo", "设置个人信息时不知道为什么出错了(Exception)");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
			
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                doGet(request,response);
	}

}
