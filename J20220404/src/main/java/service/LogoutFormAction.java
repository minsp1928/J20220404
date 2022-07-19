package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("LogoutFormAction Start...");
			String user_id = (String)request.getSession().getAttribute("user_id");
			if(user_id.equals("") || user_id.equals(null)) {
				return "main.do";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "logoutForm.jsp";
	}

}
