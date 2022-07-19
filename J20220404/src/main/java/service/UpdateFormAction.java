package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----start----updateFormAction---------");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");
		String poto = request.getParameter("poto");
		System.out.println("num->"+num);
		System.out.println("pageNum->"+pageNum);
		System.out.println("user_id->"+user_id);
		System.out.println("UFAcontent->"+content);
		System.out.println("UFApoto->"+poto);
		try {
			ComDao cd = ComDao.getInstance();
			Com com = cd.select(num);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("com", com);
			if(user_id.equals("") || user_id.equals(null)) {
				return "main.do";
			}
			else request.setAttribute("user_id", user_id);
			request.setAttribute("num",num);
			 
			System.out.println("ufa content->"+ content);
			System.out.println("num->"+num);
			System.out.println("pageNum->"+ pageNum);
			System.out.println("user_id->"+com.getUser_id());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----end----updateFormAction---------");
		return "updateForm.jsp";
	}

}
