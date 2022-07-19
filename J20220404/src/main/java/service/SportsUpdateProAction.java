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

public class SportsUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String admin_id = (String)request.getSession().getAttribute("admin_id");
		if(admin_id == null) return "main.do"; 
		
		//post 방식, 글을 받아와서 변경해줘야 반영됨.
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
			if(filename != null) {
				String pictureurl = multi.getParameter("pictureurl");
				File f = new File(realPath+"\\"+pictureurl);
				f.delete();
				System.out.println("파일 삭제됨.");
				sports.setPictureurl(filename);
			} else {
				String pictureurl = multi.getParameter("pictureurl");
				sports.setPictureurl(pictureurl);			
			}
		}	
		
		try {
			String sport_type = multi.getParameter("sport_type");
			int exnum = Integer.parseInt(multi.getParameter("exnum"));
		 
			
			sports.setSport_type(sport_type);
			sports.setExnum(exnum);
			sports.setExname(multi.getParameter("exname"));
			sports.setTime(multi.getParameter("time"));
			sports.setLoc(multi.getParameter("loc"));
			sports.setHomeurl(multi.getParameter("homeurl"));
			sports.setTel(multi.getParameter("tel"));
			sports.setExcontext(multi.getParameter("excontext"));
			
			
			upLoadFilename = realPath + "\\"+ filename;
			System.out.println("전달 upLoadFilename ->"+upLoadFilename);
			
			SportsDao sd = SportsDao.getInstance();
			int result = sd.updateSports(sports);
			
			request.setAttribute("upLoadFilename", upLoadFilename);
			request.setAttribute("filename", filename);
			request.setAttribute("result", result);
			request.setAttribute("sport_type", sport_type);
			request.setAttribute("exnum", exnum);
			
		} catch (SQLException e) {
			System.out.println("sportsUpdateProAction Err->" + e.getMessage());
		}
		
		return "sportsUpdatePro.jsp";
	}

}
