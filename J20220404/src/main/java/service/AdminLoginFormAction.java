package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("AdminLoginFormAction Start...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminLoginForm.jsp";
	}

}