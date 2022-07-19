package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SportsDao;

public class SportsWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sport_type = request.getParameter("sport_type");

		int exnum = 0;
		String user_id =(String)request.getSession().getAttribute("user_id");
        String admin_id = "";
        System.out.println(user_id);
        if(user_id == null) {
           admin_id = (String)request.getSession().getAttribute("admin_id");
        } 
        System.out.println(admin_id);
        if(admin_id  == null && user_id == null) return "index.do";
        else {
        	try {
				SportsDao sd = SportsDao.getInstance();		
				exnum = sd.exnumCount(sport_type)+1;
				
				} catch (SQLException e) {
					System.out.println("sportWriteFormAction Err" + e.getMessage());
				}
				
				request.setAttribute("sport_type", sport_type);
				request.setAttribute("exnum", exnum);
				
				return "sportsWriteForm.jsp";
        }
	}
}
