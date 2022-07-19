package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Sports;
import dao.SportsDao;

public class SportsUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String admin_id = (String)request.getSession().getAttribute("admin_id");
		if(admin_id == null) return "main.do"; 
		String sport_type = request.getParameter("sport_type");
		int exnum = Integer.parseInt(request.getParameter("exnum"));
		
		Sports sports = new Sports();
		
		try {
			SportsDao sd = SportsDao.getInstance();
			sports = sd.select(sport_type, exnum);
		
		} catch (SQLException e) {
			System.out.println("sportUpdateFormAction->" + e.getMessage());
		}
		
		request.setAttribute("sport_type", sport_type);
		request.setAttribute("sports", sports);
		
		return "sportsUpdateForm.jsp";
	}

}
