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

public class AdminOutuserAction implements CommandProcess {
	private AdminDao adminDao = AdminDao.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject rowsMapData;
		try {
			rowsMapData = adminDao.getOutuserChart();
			request.setAttribute("rowsMap", rowsMapData.toJSONString());	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "adminOutuser.jsp";
	}

}
