package service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Sports;
import dao.SportsDao;

public class SportsWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("WriteProAction start...");
		
		
		String user_id =(String)request.getSession().getAttribute("user_id");
        String admin_id = "";
        System.out.println(user_id);
        if(user_id == null) {
           admin_id = (String)request.getSession().getAttribute("admin_id");
        } 
        System.out.println(admin_id);
        if(admin_id  == null && user_id == null) return "index.do";
        else {
			request.setCharacterEncoding("utf-8");
			Sports sports = new Sports();
			String filename = "";
			String upLoadFilename="";
			
			response.setContentType("text/html;charset=utf-8");		
			int maxSize = 5 * 1024 * 1024;
			String images = "/images";
			
			String realPath = request.getServletContext().getRealPath(images);
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration en = multi.getFileNames();
			while (en.hasMoreElements()) {
				//input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
				String filename1 = (String) en.nextElement();
				//서버에 저장된 파일 이름 
				filename = multi.getFilesystemName(filename1);
				//전송전 원래의 파일 이름 
				String original = multi.getOriginalFileName(filename1);
				//전송된 파일의 내용 타입 
				String type = multi.getContentType(filename1);
				//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성 
				File file = multi.getFile(filename1);
				System.out.println("real Path : " + realPath);
				System.out.println("파라메터 이름 : " + filename1);
				System.out.println("실제 파일 이름 : " + original);
				System.out.println("저장된 파일 이름 : " + filename);
				System.out.println("파일 타입 : " + type);
				sports.setPictureurl(filename);
				if (file != null) {
					System.out.println("크기 : " + file.length());
				}
			}
			
			String sport_type = multi.getParameter("sport_type");
			int exnum = Integer.parseInt(multi.getParameter("exnum"));
			String exname= multi.getParameter("exname");
			String time = multi.getParameter("time");
			String loc = multi.getParameter("loc");
			String homeurl = multi.getParameter("homeurl");
			String tel = multi.getParameter("tel");
			String excontext=multi.getParameter("excontext");
			
			upLoadFilename = realPath + "\\"+ filename;
			System.out.println("전달 upLoadFilename ->"+upLoadFilename);
			
			//담기.
			sports.setSport_type(sport_type);
			sports.setExnum(exnum);
			sports.setExname(exname);
			sports.setExcontext(excontext);
			sports.setTime(time);
			sports.setLoc(loc);
			sports.setHomeurl(homeurl);
			sports.setTel(tel);
			
			
			try {
				
				SportsDao sd = SportsDao.getInstance();
				int result = sd.insertSports(sports);
				request.setAttribute("upLoadFilename", upLoadFilename);
				request.setAttribute("filename", filename);
				request.setAttribute("sport_type", sport_type);
				request.setAttribute("exnum", exnum);
				request.setAttribute("result", result);
	
			} catch (SQLException e) {
				System.out.println("sportWriteProActionErr" + e.getMessage());
			}
	
			return "sportsWritePro.jsp";
		}
	}

}
