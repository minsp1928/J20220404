package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;

public class IdCheckProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String uname = request.getParameter("uname");
			Long id_num = Long.parseLong(request.getParameter("id_num"));
			String tel = request.getParameter("tel");
			
			System.out.println("IdCheckProAction uname->"+uname);
			System.out.println("IdCheckProAction id_num->"+id_num);
			System.out.println("IdCheckProAction tel->"+tel);
			
			MemberDao md = MemberDao.getInstance();
			Member member = md.idCheck(uname, id_num, tel);
			
			
			request.setAttribute("member", member);
			System.out.println("IdCheckProAction member->"+member);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "idCheckPro.jsp";
	}

}
