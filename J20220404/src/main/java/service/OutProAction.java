package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;
import dao.OutUser;
import dao.OutUserDao;

public class OutProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String user_id = (String)request.getSession().getAttribute("user_id");
			if(user_id.equals("") || user_id.equals(null)) {
				return "main.do";
			}
			else request.setAttribute("user_id", user_id);
			System.out.println("OutProAction user_id->"+user_id);
			
			request.setCharacterEncoding("utf-8");
			String user_ps = request.getParameter("user_ps");
			String unregister_reason = request.getParameter("unregister_reason");
			System.out.println("OutProAction user_ps"+user_ps);
			System.out.println("OutProAction unregister_reason"+unregister_reason);
			
			MemberDao md = MemberDao.getInstance();
			
			int result = 0;
			result = md.delupdate(user_id, user_ps);
			
			OutUser outUser = new OutUser();
			outUser.setUser_id(user_id);
			outUser.setUnregister_reason(unregister_reason);
			
			OutUserDao ud = OutUserDao.getInstance();
			int result2 = 0;
			if(result != 0) {
				result2 = ud.insertOut(outUser);
			}
			
			request.setAttribute("result", result);
			request.setAttribute("result2", result2);
			System.out.println("OutProAction result->"+result);
			System.out.println("OutProAction result2->"+result2);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return "outPro.jsp";
	}
}
