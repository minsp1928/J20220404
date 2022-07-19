package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SportsDao;

public class SportsDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String admin_id = (String)request.getSession().getAttribute("admin_id");
		if(admin_id == null) return "main.do"; 
		String sport_type = request.getParameter("sport_type");
		int exnum = Integer.parseInt(request.getParameter("exnum"));
			
		try {
			
			SportsDao sd = SportsDao.getInstance();
			int result = sd.adminSportDelete(sport_type, exnum);
			request.setAttribute("result", result);
			request.setAttribute("sport_type", sport_type);
			
		} catch (SQLException e) {
			System.out.println("AdminSportDeleteProAction error->"+e.getMessage());
		}
		return "sportsDeletePro.jsp";	
	}
}
