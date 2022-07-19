package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.Admin;

public class AdminLogFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. admin_date , admin_ip
		System.out.println("AdminLogProAction.JAVA Start");
		String admin_date = request.getParameter("admin_date");
		String admin_ip = request.getRemoteAddr();
				
		try {
			// 2. AdminDao ad Instance
			AdminDao ad = AdminDao.getInstance();
			request.setCharacterEncoding("utf-8");
			// 3.
			// 4. Board board = bd.select(num);
			System.out.println("1. AdminLogProAction ");
			List<Admin> adminLogView = ad.adminLogView();
			System.out.println("2. AdminLogProAction adminLogView.size()-->"+adminLogView.size());
			
			// 5. request 객체에 date, ip
			request.setAttribute("admin_date", admin_date);
			request.setAttribute("admin_ip", admin_ip);
			request.setAttribute("adminLogView", adminLogView);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "adminLogForm.jsp";
	}

}
