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

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("--------updateProAction start--------");
			System.out.println("트라이문이 위");
		try {System.out.println("트라이문이 될까용?");
			request.setCharacterEncoding("utf-8");
			ComDao cd = ComDao.getInstance();
			Com com = new Com();
			System.out.println("com->"+com);
			
			//사진 업로드시작
			String filename = "";
			System.out.println("filename->"+filename);
			String upLoadFilename="";
			System.out.println("upLoadFilename->"+upLoadFilename);
			response.setContentType("text/html;charset=utf-8");
			int maxSize = 5*1024 * 1024; //5mb
			System.out.println("maxSize->"+maxSize);
			System.out.println();
			String fileSave = "/fileSave"; //fileSave폴더
			System.out.println("fileSave->"+fileSave);
			String realPath = request.getServletContext().getRealPath(fileSave);
			System.out.println("realPath->" + realPath);
			
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration en = multi.getFileNames();// 여기서 이미지가 올라간것
			while (en.hasMoreElements()) { //Enumeration이 있으면 hasMoreElements오타나서 안됐다 임포트
				
				String poto = (String) en.nextElement();//input태그의 속성이 file인 태그의 name 속성값 : 파라미터 이름
				filename = multi.getFilesystemName(poto);//String filename=""; // 밑에서 쓰기위해서 여기서 선언
				String original = multi.getOriginalFileName(poto);
				String type = multi.getContentType(poto);
				File file = multi.getFile(poto);// file 임포트해줘야함 file업로드하는 함수
				System.out.println("real Path : " + realPath);
				System.out.println("파라메터 이름 : " + poto);
				System.out.println("실제 파일 이름 : " + original);
				System.out.println("저장된 파일 이름: " + filename );
				System.out.println("파일 타입: " + type);
								
				if(filename == null ) {
					String dbpoto = multi.getParameter("dbpoto");//jsp에서 갖고온 name
					 filename = dbpoto;
					 System.out.println("dbpoto->"+dbpoto);
					 System.out.println("filename->"+filename);
					 
				}
				
				if (file != null) {//file이 비어있으면 값을 갖고있다는뜻
					System.out.println("크기 : " + file.length());
				}

			}   com.setPoto(filename);//
				System.out.println("파일 타입: " + com.getPoto());
			//사진업로드끝
			int num = Integer.parseInt(multi.getParameter("num"));
			System.out.println("UPAnum->"+num);
			String user_id = multi.getParameter("user_id");
			String pageNum = multi.getParameter("pageNum");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			System.out.println("num->"+num);
			System.out.println("subject->"+subject);
			System.out.println("content->"+content);
			com.setSubject(subject);
			com.setUser_id(user_id);
			com.setContent(content);
			//com.setContent(pageNum); 여기서 모든 글내용이 1이 되었다 그럼 어떻게 페이지 넘을 세팅하는
			com.setNum(num);
			int result = cd.update(com);
			System.out.println("user_id->"+com.getUser_id());
			request.setAttribute("result", result);
			request.setAttribute("num", com.getNum());
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("poto", com.getPoto());
			request.setAttribute("content", com.getContent());
			if(user_id.equals("") || user_id.equals(null)) {
				return "main.do";
			}
			else request.setAttribute("user_id", user_id);
			System.out.println("----------updateproAction.java middle test----------");
			System.out.println("result->"+result);
			System.out.println("com.getNum->"+com.getNum());
			System.out.println("pageNum->"+pageNum);
			System.out.println("user_id->"+user_id);
			System.out.println("poto->"+com.getPoto());
			System.out.println("+#+#+#+#+#+UPAcontent->"+content);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("머");
			
		}
		System.out.println("updateProAction end");
		return "updatePro.jsp";
		
	}

}
