package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Member;
import dao.MemberDao;

public class AjaxIdCheckAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			// 본인 필요 DB Text 가져 옴(DAO 연결)
			String user_id = request.getParameter("user_id");
			MemberDao md = MemberDao.getInstance();
			System.out.println("Ajaxuser_id -> "+user_id);
			int memberCnt = md.confirmId(user_id);
			System.out.println("AjaxmemberCnt -> "+memberCnt);
			if(user_id.equals("admin")) {
				memberCnt = 1;
			}
			request.setAttribute("memberCnt", memberCnt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "ajaxIdCheck"; // ajax 경우  --> 더미 Return //commandProcess를 타야 되어서 넣어준 거지, 의미 없음.
		
	}
}
