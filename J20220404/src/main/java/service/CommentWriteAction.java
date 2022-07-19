package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class CommentWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CommentWriteAction Service start !");
		String user_id = request.getSession().getAttribute("user_id").toString();
		if(user_id  == null) {
			System.out.println("ContentAction if test");
			return "main.do";
		}
		else {
			// re_content=dddd&recnnum=&comnum=105&pageNum=1
			System.out.println("ContentAction else");
			int result = 0;
			String re_content = request.getParameter("re_content");
			System.out.println(re_content);
			if(re_content == null) {
				System.out.println("ContentAction re_content NULL");
				request.setAttribute("result", result);
			}
			else {
				request.setCharacterEncoding("utf-8");
				ComDao cd = ComDao.getInstance();
//				int recnnum = Integer.parseInt(request.getParameter("recnnum"));
				int comnum = Integer.parseInt(request.getParameter("comnum"));
				String pageNum = request.getParameter("pageNum");
//				System.out.println("CWA recnnum->"+recnnum);
				System.out.println("CWA comnum->"+comnum);
				System.out.println("CWA pageNum->"+pageNum);
				System.out.println("체크용");
				try {
					// comnum== pk num
					Com com = cd.select(comnum);
					System.out.println("try문 체크1");
					com.setRe_content(request.getParameter("re_content"));
					System.out.println("CWA (request.getParameter(re_content))->"+(request.getParameter("re_content")));
					com.setUser_id(user_id);
					result = cd.commInsert(com);//댓글 인서트
					List<Com> hashList = cd.hashList(comnum);//해쉬태그
					List<Com> recnList = cd.recnList(comnum);//댓글수
					request.setAttribute("hashList", hashList); 
					request.setAttribute("com",com);  
					request.setAttribute("recnList", recnList); 
					request.setAttribute("poto", com.getPoto());
					request.setAttribute("content", com.getContent());
					System.out.println("CA try com.getContent()->"+com.getContent());
					request.setAttribute("pageNum", pageNum);
//					request.setAttribute("recnnum",recnnum);
					request.setAttribute("comnum", comnum);
					System.out.println("CA pageNum->"+pageNum);
					
					request.setAttribute("result", result);
				} catch (Exception e) {
					System.out.println("CommentWriteAction 에러 ->" +e.getMessage());
				}
	
			} 
			return "commentWrite.jsp";
		
		}
	}

}
