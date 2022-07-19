package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;

public class QaListAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("ReviewListAction service Start...");
      ComDao cd = ComDao.getInstance();
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
            //리퀘스트 받으라함
//             int com_type = Integer.parseInt(request.getParameter("com_type")); 
            int com_type = 102;
            int totCnt = cd.getTotalCnt(com_type); // 안쓸수도있음
            String pageNum = request.getParameter("pageNum");
            if(pageNum == null || pageNum.equals("")) { // 처음 실행시키면 페이지번호 1 
               pageNum = "1";
            }
            int currentPage = Integer.parseInt(pageNum);
            int pageSize = 5; // 한 페이지에 보여주는 게시물의 갯수
            int blockSize = 10; // 페이지 갯수, 10개가 넘어가면 다음 11부터 되는 페이지
            
            int startRow = (currentPage -1) * pageSize +1;
            int endRow = startRow + pageSize -1;
            // 스타트 로가 1이고, 페이지 사이즈가 10니까 11-1= 10
            System.out.println("totCnt->"+totCnt);
            int startNum = totCnt - startRow +1;// 안쓸수도있음
            System.out.println("startNum"+startNum);
   
            List<Com> reviewList = cd.reviewList(startRow, endRow, com_type);
            
            int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
            // 토탈카운트 6/ 페이지 사이즈 5개 그거 나눈 값에 세일 하면 올림 이니까 현재 2페이지로 보여줌
            int startPage = (int)(currentPage-1)/blockSize*blockSize +1;
            // 현재를 기준으로 스타트 페이지 1
            int endPage = startPage + blockSize-1;
            // 1+ 블록 사이즈가 2이니까 결구 2페이지
            if (endPage > pageCnt) endPage = pageCnt;
            // 엔드페이지가 10이 페이지카운트가 2니까 엔드가 더 크니까 이럴댄 두개를 같게 해줘라 하는것-> 공갈페이지 방지목적
            // 여태 계산한것 다 담아서 페이지 작업할때 뿌려줘라
            
            String inNumStr = "(";
            for(Com comHash : reviewList ) {
               //Com com = new Com();
               if (inNumStr.equals("("))    inNumStr = inNumStr + comHash.getNum();
               else                        inNumStr =  inNumStr + "," + comHash.getNum();
            
            }
            inNumStr = inNumStr + ')';
            
            System.out.println("inNumStr->"+inNumStr);
            List<Com> rehashList = cd.rehashList(inNumStr);
            
            
            System.out.println("rehashList.size()->"+rehashList.size());
            request.setAttribute("totCnt", totCnt);
            request.getSession().setAttribute("pageNum", currentPage);
            request.setAttribute("pageNum", pageNum); 
            request.setAttribute("currentPage",currentPage);
            request.setAttribute("startNum", startNum);
            request.setAttribute("reviewList", reviewList);
            request.setAttribute("blockSize", blockSize);
            request.setAttribute("pageCnt", pageCnt);
            request.setAttribute("startPage", startPage);
            request.setAttribute("endPage", endPage);
            request.setAttribute("rehashList", rehashList);
            request.setAttribute("inNumStr", inNumStr);
         } catch (Exception e) {
            System.out.println("reviewListAction error=>"+e.getMessage());
         }
         return "qaList.jsp";
      }
   }
}