package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Inter;
import dao.InterDao;
import dao.Member;
import dao.MemberDao;

public class MyPageAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user_id") == null) {
			return "loginForm.do";
		}
		/*
		 * else if(request.getSession().getAttribute("admin_id").toString() == null) {
		 * return "loginForm.do"; }
		 */
		else {
			try {
				System.out.println("MyPageAction Start...");
				String user_id = request.getSession().getAttribute("user_id").toString();
				MemberDao md = MemberDao.getInstance();
				Member member = md.selectMemberInfo(user_id);
				InterDao io = InterDao.getInstance();
				Inter inter = io.selectInterInfo(user_id);
				//HW
				System.out.println("MyPageAction member.getUser_picture()->"+member.getUser_picture());
				// Me
				System.out.println("MyPageAction inter.getSport_type()->"+inter.getSport_type());
						
				request.setAttribute("member", member);
				request.setAttribute("inter", inter);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "myPage.jsp";
		}
		
	}

}
