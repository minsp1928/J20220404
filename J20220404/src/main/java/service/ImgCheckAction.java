package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheckSports;
import dao.CheckSportsDao;

public class ImgCheckAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("아작스를 아작");
		try {		
			request.setCharacterEncoding("utf-8"); 
			response.setContentType("text/html;charset=utf-8");
			
			// 본인 필요 DB Text가져 옴 (DAO 연결)
			String user_id = request.getParameter("user_id");
			String sport_type = request.getParameter("sport_type");
			int exnum = Integer.parseInt(request.getParameter("exnum"));
			int result = 0;
			
			System.out.println("user_id ->" + user_id);
			System.out.println("sport_type ->" + sport_type);
			System.out.println("exnum ->" + exnum);
			
			CheckSports checkSports = new CheckSports();
			checkSports.setUser_id(user_id);
			checkSports.setSport_type(sport_type);
			checkSports.setExnum(exnum);
			
			CheckSportsDao cd = CheckSportsDao.getInstance();
			
			if(cd.checkLike(checkSports) == 0) {
				result = cd.insertLike(checkSports);
				if(result > 0) {
					request.setAttribute("like", "like");
				} else {
					request.setAttribute("like", "dislike");
				}
				
			} else {
				cd.deleteLike(checkSports);
				if(result > 0) {
					request.setAttribute("like", "dislike");
				} else {
					request.setAttribute("like", "like");
				}
			
			}
			
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		} 	
		
		return "imgcheck";
	}

}
