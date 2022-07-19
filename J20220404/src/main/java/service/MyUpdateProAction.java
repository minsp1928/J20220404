package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Inter;
import dao.InterDao;
import dao.Member;
import dao.MemberDao;

public class MyUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				System.out.println("MyUpdateProAction start.. ");
				String user_id = (String)request.getSession().getAttribute("user_id");
				if(user_id.equals("") || user_id.equals(null)) {
					return "main.do";
				}
				else request.setAttribute("user_id", user_id);
				System.out.println("MyUpdateProAction user_id->"+user_id);
				
				request.setCharacterEncoding("utf-8");
				
			//  5M
				int maxSize= 5 * 1024 * 1024;   
				String fileSave = "/fileSave";
				//  MetaData
				String realPath= request.getSession().getServletContext().getRealPath(fileSave);
				System.out.println("realPath->"+realPath);                      //maxSize를 정하지 않으면 공격 당할 수 있다.
				MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy()); //lib 폴더 안에 4개 항상 갖고 다니기(사진 추가할 때 특히!!)
				Enumeration en= multi.getFileNames(); 
				//서버에 저장될 파일 이름
				String filename = "";
				//디폴트 정책으로 한다는 의미
				while (en.hasMoreElements()) {
					//input 태그의 속성이 file인 태그의 name 속성값: 파라미터이름
					String filename1 = (String) en.nextElement();
					//서버에 저장된 파일 이름
					filename = multi.getFilesystemName(filename1);
					//전송전 원래의 파일 이름
					String original = multi.getOriginalFileName(filename1);
					//전송된 파일의 내용 타입
					String type = multi.getContentType(filename1);
					//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성
					File file = multi.getFile(filename1);
					System.out.println("real Path : " + realPath + "<br>");
					System.out.println("파라메터이름 : " + filename1 + "<br>");
					System.out.println("실제 파일 이름 : " + original + "<br>");
					System.out.println("저장된 파일 이름 : " + filename + "<br>");
					System.out.println("파일 타입 : " + type + "<br>");                
					if(file !=null) {
						System.out.println("크기 : " + file.length() + "<br>");
					}
				}
				request.setCharacterEncoding("utf-8");
				Member member = new Member();
				Inter inter = new Inter();
				if(filename == null) {
					String user_picture = multi.getParameter("user_picture");
					filename = user_picture;
				}
				member.setUser_id(user_id);
				member.setUser_ps(multi.getParameter("user_ps"));
				member.setUname(multi.getParameter("uname"));
				// ***
				member.setId_num(Long.parseLong(multi.getParameter("id_num")));
				
				member.setGender(multi.getParameter("gender"));
				member.setUser_picture(filename);
				member.setTel(multi.getParameter("tel"));
				inter.setUser_id(user_id);
				inter.setSport_type(multi.getParameter("sport_type"));
				System.out.println("MyUpdateProAction inter user_id : " + user_id);                
				System.out.println("MyUpdateProAction inter sport_type 타입 : " + multi.getParameter("sport_type"));                
				
				MemberDao md = MemberDao.getInstance();
				int result = md.updateMember(member);
				
				InterDao io = InterDao.getInstance();
				int result2 = io.updateSport(inter);
				
				
				System.out.println("MyUpdateProAction result->"+result);
				System.out.println("MyUpdateProAction result2->"+result2);
				
				request.setAttribute("result",  result);
				request.setAttribute("result2", result2);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return "myUpdatePro.jsp";
		}

	}
