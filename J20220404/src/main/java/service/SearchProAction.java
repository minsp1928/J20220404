package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Com;
import dao.ComDao;
import dao.Sports;
import dao.SportsDao;


public class SearchProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String user_id;
			String search = request.getParameter("search");
			ComDao comDao = ComDao.getInstance();
			List<Com> comSub = comDao.SearchSubject(search);
			List<Com> comContent = comDao.SearchContents(search);			
			SportsDao spoDao = SportsDao.getInstance();
			List<Sports> spoSub = spoDao.SearchSubject(search);
			List<Sports> spoContent = spoDao.SearchContents(search);
			
			int searchLength=search.length();	
			int subCnt = comSub.size()+spoSub.size();
			int contentCnt = comContent.size()+spoContent.size();
			
			if(request.getSession().getAttribute("user_id") == null) {
				user_id = null;
			}
			else {
				user_id = request.getSession().getAttribute("user_id").toString();
			}
			
			request.setAttribute("user_id", user_id);
			request.setAttribute("searchLength", searchLength);
			request.setAttribute("subCnt", subCnt);
			request.setAttribute("comSub", comSub);
			request.setAttribute("spoSub", spoSub);
			request.setAttribute("search", search);
			request.setAttribute("spoContent", spoContent);
			request.setAttribute("comContent", comContent);
			request.setAttribute("contentCnt", contentCnt);
			
			}catch (Exception e) {
			}
			return "search.jsp";
		}

	}
