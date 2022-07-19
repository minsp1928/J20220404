package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Admin;
import dao.AdminDao;
import dao.Member;
import dao.MemberDao;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			String user_ps = request.getParameter("user_ps");
			
			System.out.println("LoginProAction user_id->"+user_id);
			System.out.println("LoginProAction user_ps->"+user_ps);
			int result = 0;
			/*if(user_id.equals("admin")  && user_ps.equals("admin") ) {
				
				AdminDao ad = AdminDao.getInstance();
				System.out.println("LoginProAction check before..");
				result = ad.check(user_id, user_ps);
				System.out.println("LoginProAction check after result->"+result);
			}
			else {*/
				Member member = new Member();
				member.setUser_id(request.getParameter("user_id"));
				member.setUser_ps(request.getParameter("user_ps"));
				MemberDao md = MemberDao.getInstance();
				result = md.check(user_id, user_ps);
				
				System.out.println(result);
				/* } */
			
			
			request.setAttribute("result", result);
			request.getSession().setAttribute("user_id", user_id);
			request.getSession().setAttribute("user_ps", user_ps);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "loginPro.jsp";
	}

}
