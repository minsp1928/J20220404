package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class ContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ContentAction Service start !");
		String user_id =(String)request.getSession().getAttribute("user_id");
		String admin_id = "";
		System.out.println(user_id);
		if(user_id == null) {
			admin_id = (String)request.getSession().getAttribute("admin_id");
		} 
		System.out.println(admin_id);
		if(admin_id  == null && user_id == null) return "index.do";
		else {
			try {
				ComDao cd = ComDao.getInstance();
				System.out.println("ComDao cd->" + cd);
				String pageNum = request.getParameter("pageNum");
				System.out.println("pageNum->" + pageNum);
				int num = Integer.parseInt(request.getParameter("num"));
				System.out.println("num->" + num);
				System.out.println("CA try문이 도는지 체크1");
				int comCnt = cd.getComCnt(num); //댓글
				System.out.println("try문 ContentAction Service start !");
				cd.count(num); // 조회수증가 메서드
				Com com = cd.select(num);// select문
				Com com2 = cd.contentSelect(user_id);//로그인한 유저아이디와 글,댓글을 작성한 유저아이디를 비교하기위한 select문
				
				List<Com> hashList = cd.hashList(num);// 해쉬태그
				List<Com> recnList = cd.recnList(num);// 댓글리스트
				String com_type = Integer.toString(com.getCom_type());//컴에서 가져온int형을 string형으로 변환 (mcd가 string형임)
				String com_type_str = cd.comtSelect(com_type);
				System.out.println("CA try문이 도는지 체크2");

				request.setAttribute("comCnt", comCnt);
				request.setAttribute("hashList", hashList);
				request.setAttribute("num", num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("com", com);
				request.setAttribute("com_type", com.getCom_type());
				request.setAttribute("recnList", recnList);
				request.setAttribute("poto", com.getPoto());
				request.setAttribute("content", com.getContent());
				request.setAttribute("com_type_str", com_type_str);
			
				 
				System.out.println("ContentAction try com.getContent()->" + com.getContent());
				for (int j = 0; j < recnList.size(); j++) {// recn확인용
					System.out.println("ContentAction try recnList getNum()->" + recnList.get(j).getNum());
					System.out.println("ContentAction try recnList getContent()->" + recnList.get(j).getContent());
					System.out.println("ContentAction try recnList getRe_step()->" + recnList.get(j).getRe_step());
				}

				System.out.println("CAuser_id->" + user_id);

				System.out.println("CA try문이 도는지 체크3");

				System.out.println("user_id->" + user_id);
				System.out.println("recnList->" + recnList);
				System.out.println("hashList->" + hashList);
				System.out.println("num->" + num);
				System.out.println("pageNum->" + pageNum);
				System.out.println("com->" + com);
				System.out.println("CAcom.getPoto()->" + com.getPoto());
				System.out.println("CAcom.com_type_str->" + com_type_str);

				System.out.println("CA try문 comCnt->" + comCnt);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			return "content.jsp";
		}
	}

}
