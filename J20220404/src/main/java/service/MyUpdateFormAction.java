package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Inter;
import dao.InterDao;
import dao.Member;
import dao.MemberDao;

public class MyUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("MyPageAction Start...");
			String user_id = request.getSession().getAttribute("user_id").toString();
			MemberDao md = MemberDao.getInstance();
			Member member = md.selectMemberInfo(user_id);
			InterDao io = InterDao.getInstance();
			Inter inter = io.selectInterInfo(user_id);
			
			System.out.println("MyUpdateFormAction member.getUser_picture()->"+member.getUser_picture());
			System.out.println("MyUpdateFormAction inter.getSport_type()->"+inter.getSport_type());
					
			request.setAttribute("member", member);
			request.setAttribute("inter", inter);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "myUpdateForm.jsp";
	}

}
