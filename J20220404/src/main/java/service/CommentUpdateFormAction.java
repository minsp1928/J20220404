package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class CommentUpdateFormAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("----시이작----commentUpdateFormAction---------");

      if(request.getSession().getAttribute("user_id") == null) {
         System.out.println("ContentAction session test");
         return "main.do";
      }
      else {
         int recnnum = Integer.parseInt(request.getParameter("recnnum"));
         int comnum = Integer.parseInt(request.getParameter("comnum"));
         
         String pageNum = request.getParameter("pageNum");
         String user_id = request.getSession().getAttribute("user_id").toString();
         
         System.out.println("댓글의 글번호 recnnum->"+recnnum); // 댓글의 글번호 
         System.out.println("pageNum->"+pageNum);
         System.out.println("user_id->"+user_id);
         System.out.println(" 원 글의 글번호 comnum->"+recnnum);
         
         try {
            ComDao cd = ComDao.getInstance();
            Com com = cd.select(recnnum);
            
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("com", com);
            request.setAttribute("recnnum",recnnum);
            request.setAttribute("comnum", comnum);
      
            
         } catch (Exception e) {
            System.out.println("CommentUpdateFormAction에러러러러러러럴ㄹ==>"+e.getMessage());
         }
         System.out.println("----end----commentUpdateFormAction---------");
         return "commentUpdateForm.jsp";
      }
   
   }

}