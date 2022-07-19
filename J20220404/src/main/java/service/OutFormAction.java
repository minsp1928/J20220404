package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OutFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			System.out.println("OutFormAction Start...");
			String user_id = (String)request.getSession().getAttribute("user_id");

			if(user_id.equals("") || user_id.equals(null)) {
				return "main.do";
			}
			else request.setAttribute("user_id", user_id);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "outForm.jsp";
	}

}
