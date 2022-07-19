package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ComDao;

public class DeleteProAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   	String user_id =(String)request.getSession().getAttribute("user_id");
	   	String admin_id = "";
	   	System.out.println(user_id);
	   	if(user_id == null) {
	   		admin_id = (String)request.getSession().getAttribute("admin_id");
	   	} 
	   	System.out.println(admin_id);
	   	if(admin_id  == null && user_id == null) return "index.do";
	   	else {
   			System.out.println("------------deleteProAction 시작----------------");
   			int num = Integer.parseInt(request.getParameter("num"));
   			String pageNum = request.getParameter("pageNum");
   			System.out.println("num ?????======> "+ num);
   			try {
	            ComDao cd = ComDao.getInstance();
	            int result = cd.delete(num);
	            System.out.println("Delete result결과값!! 성공여뷰====>"+result);
	            request.setAttribute("num", num);
	            request.setAttribute("pageNum", pageNum);
	            request.setAttribute("result", result);
	         } catch (Exception e) {
	            System.out.println("deletePro 오류류류===> " +e.getMessage());
	         }
	         return "deletePro.jsp";
	   	}
   	}
}