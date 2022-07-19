package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Com;
import dao.ComDao;

public class WriteProAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
   
      if(request.getSession().getAttribute("user_id") == null) {
         System.out.println("ContentAction session test");
         return "main.do";
      }
      else {
         request.setCharacterEncoding("utf-8");
         ComDao cd = ComDao.getInstance();
         Com com = new Com();
         
         String filename = "";
         String upLoadFilename = "";
         
         response.setContentType("text/html;charset=utf-8");
         int maxSize = 5*1024 * 1024;
         String fileSave = "/fileSave";
         
         String realPath = request.getServletContext().getRealPath(fileSave);
         System.out.println("1. realPath->"+realPath);
         MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
         
         Enumeration en = multi.getFileNames();
         while(en.hasMoreElements()) {
            String poto = (String)en.nextElement(); // //input 태그 속성이 file인 태그의 name 속성값 : 파라미터이름
            
            filename = multi.getFilesystemName(poto);
            String original = multi.getOriginalFileName(poto);
            String type = multi.getContentType(poto);
            File file = multi.getFile(poto);
            System.out.println("real Path : " + realPath);
            System.out.println("파라메터 이름 : " + poto);
            System.out.println("실제 파일 이름 : " + original);
            System.out.println("저장된 파일 이름 : " + filename);
            System.out.println("파일 타입 : " + type);
            com.setPoto(filename);
            
            if(file !=null) {
               System.out.println("사진파일크기 : "+file.length());
            }
         } 
         
         try {
            System.out.println("글내용 content->"+multi.getParameter("content"));
            int com_type = Integer.parseInt(multi.getParameter("com_type"));
            String pageNum = multi.getParameter("pageNum");
            com.setNum(Integer.parseInt(multi.getParameter("num")));
            com.setUser_id(request.getSession().getAttribute("user_id").toString());
            com.setCom_type(com_type);
            com.setSubject(multi.getParameter("subject"));
            com.setContent(multi.getParameter("content"));
            
            com.setRef(Integer.parseInt(multi.getParameter("ref")));
            com.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
            com.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
            
            upLoadFilename = realPath + "\\"+ filename;
            System.out.println("전달 upLoadFilename ->"+upLoadFilename);
            
            int result = cd.insert(com);
            
            request.setAttribute("upLoadFilename", upLoadFilename);
            request.setAttribute("filename", filename);
            request.setAttribute("com_type", com_type);
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("num", com.getNum());
            request.setAttribute("result", result);
            
         } catch (Exception e) {
            System.out.println("WriteProAction 에러 ->" +e.getMessage());
         }
         
         return "writePro.jsp";
      }
   }

}