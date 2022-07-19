package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SportsDao;

public class AdminSportsDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user_id") == null) {
			System.out.println("AdminSportsDeleteProAction session test");
			return "main.do";
		}
		else {
			try {
				String sport_type = request.getParameter("sport_type");
				int exnum = Integer.parseInt(request.getParameter("exnum"));
				SportsDao sd = SportsDao.getInstance();
				int result = sd.adminSportDelete(sport_type, exnum);
				request.setAttribute("result", result);
			} catch (SQLException e) {
				System.out.println("AdminSportDeleteProAction error->"+e.getMessage());
			}
			return "adminSportsDeletePro.jsp";
		}
	}

}
