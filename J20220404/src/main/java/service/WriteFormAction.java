package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;


public class WriteFormAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      if(request.getSession().getAttribute("user_id") == null) {
         System.out.println("ContentAction session test");
         return "main.do";
      }
      else {
         try {
            int num = 0, ref = 0, re_level=0, re_step=0;
            String pageNum = request.getParameter("pageNum");
            if(pageNum == null) pageNum="1";
            // 댓글일 경우
             // 신규 글이면 num값이 content에서 num이 넘어올때 null로 넘어오니까 신규니까 새로 부여받겟지
            // 그런null이니까 if문이 실행되지않고 넘어감 그래서 신규글이면 writeForm으로 넘어갈때 num_ref0이런식으로
            //다 0을 가져가겟지만 댓글이면 신규가아니니까 ref를 이대로 담아서 넘어가게됨
            
            request.setAttribute("num", num);
            request.setAttribute("ref", ref); //0
            request.setAttribute("re_level", re_level);//0
            request.setAttribute("re_step", re_step); //0
            request.setAttribute("pageNum", pageNum); //pageNum="1"
            
            
         } catch (Exception e) {
            // TODO: handle exception
         }
         return "writeForm.jsp";   
      }
   }

}