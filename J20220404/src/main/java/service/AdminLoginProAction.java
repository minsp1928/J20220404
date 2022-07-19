package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;

public class AdminLoginProAction implements CommandProcess {
	private AdminDao adminDao = AdminDao.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		request.setCharacterEncoding("utf-8");
		String adminId = request.getParameter("admin_id");
		String adminPs = request.getParameter("admin_ps");
		
		int result = adminDao.adminCheck(adminId, adminPs);
		if (result == 1) {
			adminDao.adminLoginsert(request.getRemoteAddr()); 
		}
		
		request.setAttribute("result", result);
		request.getSession().setAttribute("admin_id", adminId);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return "adminLoginPro.jsp";
	}

}
