package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class DeleteFormAction implements CommandProcess {

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
		   System.out.println("------------deleteFormAction 시작----------------");
		   int num = Integer.parseInt(request.getParameter("num"));
		   String pageNum = request.getParameter("pageNum");
		   System.out.println("num ????? ======> " + num);
		   try {
	            ComDao cd = ComDao.getInstance();
	            Com com = cd.select(num);
	            request.setAttribute("num", num);
	            request.setAttribute("pageNum", pageNum);
	            request.setAttribute("com", com);
	            System.out.println("---------------- 2. deleteFormAction 시작----------");
            
		   } catch (Exception e) {
			   System.out.println("deleteFormAction 시작서비스 문제 => " + e.getMessage());
		   }
         return "deleteForm.jsp";
      }
   }

}