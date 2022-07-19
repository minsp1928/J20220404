package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.AdminDao;

public class AdminInterSportsAction implements CommandProcess {
	
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminDao adminDao = AdminDao.getInstance();
		JSONObject rowsMapData;
		try {
			rowsMapData = adminDao.getInterSportsChart();
			request.setAttribute("rowsMap", rowsMapData.toJSONString());	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "adminInterSports.jsp";
	}

}
