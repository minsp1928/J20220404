package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class CommentUpdateProAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      System.out.println("--------commentUpdateProAction start--------");

      if (request.getSession().getAttribute("user_id") == null) {
         System.out.println("ContentAction session test");
         return "main.do";
         
      } else {
         request.setCharacterEncoding("utf-8");
         
         try {
            System.out.println("--------commentUpdateProAction start2--------");
            int recnnum = Integer.parseInt(request.getParameter("recnnum"));
            int comnum = Integer.parseInt(request.getParameter("comnum"));
            
            String pageNum = request.getParameter("pageNum");
            String re_content = request.getParameter("re_content");
            int ref = Integer.parseInt(request.getParameter("ref"));
            int re_level = Integer.parseInt(request.getParameter("re_level"));
            int re_step = Integer.parseInt(request.getParameter("re_step"));
            
            System.out.println("!!!!!!!!!!!! ref ????????====>"+ref);
            System.out.println("!!!!!!!!!!!! re_level ????????====>"+re_level);
            System.out.println("!!!!!!!!!!!! re_step ????????====>"+re_step);
            System.out.println();
            System.out.println("recnnum 댓글의 글번호 ????????====>"+recnnum);
            System.out.println("comnum 원래 글의  글번호 ????????====>"+comnum);
            System.out.println("pageNum ????????====>"+pageNum);
            System.out.println("re_content 수정한글 ????????====>"+re_content);
            
            String user_id = request.getSession().getAttribute("user_id").toString();
            
            ComDao cd = ComDao.getInstance();
            Com com = new Com();
            
            com.setUser_id(user_id);
            com.setNum(recnnum);
            com.setRe_content(re_content);
            com.setRef(ref);
            com.setRe_level(re_level);
            com.setRe_step(re_step);
            System.out.println("recnNum"+recnnum);
            int result = cd.commentUpdate(com);
            
            System.out.println("DB업데이트 후의 re_content?>==>"+com.getRe_content());
            System.out.println("DB업데이트 후의 user_id?>==>"+com.getUser_id());
            System.out.println("DB업데이트 후의 댓글의 num?>==>"+com.getNum());
            System.out.println("DB업데이트 후의  원래 글의  글번호 ????????====>"+comnum);
            request.setAttribute("result", result);
            request.setAttribute("recnnum", com.getNum());
            request.setAttribute("re_content", com.getRe_content());
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("comnum", comnum);
            
            System.out.println("commentUpdate Pro Action 마지막 ==>");
            
         } catch (Exception e) {
            System.out.println("CommentProAction 의 에러러러 => "+e.getMessage());
         }
         return "commentUpdatePro.jsp";
      }
   }

}