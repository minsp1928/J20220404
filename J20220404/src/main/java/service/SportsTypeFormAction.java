package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Sports;
import dao.SportsDao;

public class SportsTypeFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sport_type = request.getParameter("sport_type");
		String context = request.getContextPath();
		
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
			List<Sports> list = null;
			int totCnt = sd.getTotalCnt(sport_type);
			
			if(admin_id == "admin") {
				list = sd.adminSportList(sport_type);
			} else {
				list = sd.sportList(sport_type, user_id);
			}
			 	
			request.setAttribute("context", context);
			request.setAttribute("sport_type", sport_type);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("list", list);
			
           } catch (SQLException e) {
        	   System.out.println("sportsTypeFormAction Err -> " + e.getMessage());
           }
           return "sportsTypeForm.jsp";
        }
	}

}
