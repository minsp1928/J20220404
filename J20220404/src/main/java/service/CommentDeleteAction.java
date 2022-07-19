package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class CommentDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("CommentDeleteAction Service start !");
			
			String user_id =(String)request.getSession().getAttribute("user_id");
			String admin_id = "";
			System.out.println(user_id);
			if(user_id == null) {
				admin_id = (String)request.getSession().getAttribute("admin_id");
			} 
			System.out.println(admin_id);
			if(admin_id  == null && user_id == null) return "index.do";
			else {
				int recnnum=Integer.parseInt(request.getParameter("recnnum"));
				int comnum=Integer.parseInt(request.getParameter("comnum"));
				String pageNum = request.getParameter("pageNum");
					
				System.out.println("CDA comnum->"+comnum);
				System.out.println("CDA recnnum->"+recnnum);
				System.out.println("CDA pageNum->"+pageNum);
				System.out.println("체크용");
				try {
					System.out.println("CDAtry문 체크1");
					request.setCharacterEncoding("utf-8");
					ComDao cd = ComDao.getInstance();
					int result = cd.commDelete(recnnum); 
					Com com = cd.select(comnum);
					List<Com> hashList = cd.hashList(comnum);//해쉬태그
					List<Com> recnList = cd.recnList(comnum);//댓글수
						
					request.setAttribute("hashList", hashList); 
					request.setAttribute("com",com);  
					request.setAttribute("recnList", recnList); 
					request.setAttribute("poto", com.getPoto());
					request.setAttribute("content", com.getContent());
					System.out.println("CA try com.getContent()->"+com.getContent());
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("recnnum", com.getNum());
					request.setAttribute("comnum", comnum);
						
					request.setAttribute("result", result);
				} catch (Exception e) {
					System.out.println("CommenDeleteAction 에러 ->" +e.getMessage());
				}
			}
			return "contentDelete.jsp";
		}
	}