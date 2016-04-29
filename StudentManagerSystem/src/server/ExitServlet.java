package server;
//write by uchiyou

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitServlet extends HttpServlet {
/*
 * public class HttpServletResponseWrapper extends ServletResponseWrapper  
        implements HttpServletResponse {
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.setMaxInactiveInterval(1);
		Cookie[] cookie=req.getCookies();
		for(Cookie cook:cookie){
			cook.setMaxAge(1);
			resp.addCookie(cook);
		}

		resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.setMaxInactiveInterval(1);
		Cookie[] cookie=req.getCookies();
		for(Cookie cook:cookie){
			cook.setMaxAge(1);
			resp.addCookie(cook);
		}

		resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
		//req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
	}
	
	// write by uchiyou
}
