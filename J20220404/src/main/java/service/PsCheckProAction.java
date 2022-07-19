package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;

public class PsCheckProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			Long id_num = Long.parseLong(request.getParameter("id_num"));
			String tel = request.getParameter("tel");
			
			System.out.println("PsChecProAction user_id->"+user_id);
			System.out.println("PsChecProAction id_num->"+id_num);
			System.out.println("PsChecProAction tel->"+tel);
			
			MemberDao md = MemberDao.getInstance();
			Member member = md.psCheck(user_id, id_num, tel);
			
			request.setAttribute("member", member);
			System.out.println("PsChecProAction member->"+member);
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "psCheckPro.jsp";
	}

}
