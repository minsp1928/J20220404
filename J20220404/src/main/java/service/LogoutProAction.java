package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("LogoutAction Start...");
//			if(request.getSession().getAttribute("user_id") != null) {
//				request.getSession().removeAttribute("admin_id");
//				request.getSession().setAttribute("user_id", null);
//				request.getSession().invalidate();
//			}
//			else if(request.getSession().getAttribute("admin_id") != null) {
//				request.getSession().removeAttribute("admin_id");
//				request.getSession().setAttribute("admin_id", null);
//				request.getSession().invalidate();
//			}
			request.getSession().invalidate();
//			request.getSession().setMaxInactiveInterval(0);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "main.do";
	}

}
