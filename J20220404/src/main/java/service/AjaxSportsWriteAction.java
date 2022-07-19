package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SportsDao;

public class AjaxSportsWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("두번째 아작스");
		
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		
		int exnum = 0;
		
		//본인 필요 DB 가져옴
		String sport_type = request.getParameter("sport_type");
		System.out.println("sport_type->" + sport_type);
		try {
			SportsDao sd = SportsDao.getInstance();		
			exnum = sd.exnumCount(sport_type)+1;	
		} catch (SQLException e) {
			System.out.println("ajaxWriteFrom" + e.getMessage());
		}
		System.out.println("exnum->" + exnum);
		
		//아작스는 int형을 String형으로 
		String result = Integer.toString(exnum);
		System.out.println("result : " + result);
		request.setAttribute("result", result);
		
		return "ajaxSportsWrite";
	}

}
