package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Sports;
import dao.SportsDao;

public class AdminSportsAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("action start");

		String admin_id = (String)request.getSession().getAttribute("admin_id");
		if(admin_id == null) return "main.do"; 

		String sport_type = request.getParameter("sport_type");		
		int startNum = 1;
		
		if(sport_type == null || sport_type.equals("")) sport_type = "0";
		
		SportsDao sd = SportsDao.getInstance();
		
		try {
			int totCnt = sd.getTotalCnt(sport_type);
			List<Sports> list = sd.adminSportList(sport_type);
				
			request.setAttribute("sport_type", sport_type);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("list", list);
			request.setAttribute("startNum", startNum);
			
		} catch (SQLException e) {
			System.out.println("adminSportsAction Err -> " + e.getMessage());
		}
		
		return "adminSports.jsp";
	}

}
