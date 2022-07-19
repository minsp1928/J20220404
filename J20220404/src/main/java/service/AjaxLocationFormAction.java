package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Sports;
import dao.SportsDao;

public class AjaxLocationFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AjaxLocationFormAction Start..");
		if(request.getSession().getAttribute("user_id").toString() == null) {
			System.out.println("AjaxLocationFormAction Start null");
			return "loginForm.do";
		}
		else {
			try {
				System.out.println("AjaxLocationFormAction Start else");
				String user_id = request.getParameter("user_id");
				String list = request.getParameter("list");
				System.out.println("user_id->"+user_id);
				System.out.println("list->"+list);
				request.setAttribute("list", list);
				
			} catch(Exception e) { System.out.println(e.getMessage()); }
	        return "ajax2";
		}
	}

}
