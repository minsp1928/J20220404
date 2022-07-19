package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.Sports;
import dao.SportsDao;
import service.CommandProcess;

/**
 * Servlet implementation class Controller
 */
/* @WebServlet("/Controller") */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//			/list.do=service.ListAction
    private Map<String, Object> commandMap = new HashMap<String, Object>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    /*
     * My Role
     * 1) /WEB-INF/command.properties Read
     * 2) Properties 변환 --> Memory Load
     * 3) Map 변환
     */
	public void init(ServletConfig config) throws ServletException {
		// web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
	    String props = config.getInitParameter("config");
	    // /WEB-INF/command.properties 라는 값을 가져옴.
	    System.out.println("String props => "+props);
	    // 명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
	    Properties pr = new Properties();
	    FileInputStream f = null;
	    try {
	    														// /WEB-INF/command.properties
	    	String configFilePath = config.getServletContext().getRealPath(props);
	    	System.out.println("configFilePath => "+configFilePath);
			f = new FileInputStream(configFilePath);
			//command.properties파일의 정보를 Properties객체에 저장
			pr.load(f);
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if(f!=null)
				try {
					f.close();
				}catch (IOException ex) { }
		}
	    //Iterator 객체는 Enumeration 객체를 확장시킨 개념의 객체
	    Iterator keyIter = pr.keySet().iterator();
	    //객체를 하나씩 꺼내서 그 객체명으로 Properties객체에 저장된 객체에 접근
	    while(keyIter.hasNext()) {
	    	String command = (String)keyIter.next();			// /list.do가 들어감. 얘는 Key
	    	String className = pr.getProperty(command);			// service.ListAction
	    	System.out.println("className => "+className);
	    	try {
				/* ListAction la = new ListAction(); 이게 밑에 두줄이랑 동일 */ 
	    		// 왜 이걸안쓰고 밑에를 쓰냐 Controller를 안건들기 위해서다.
	    		// instance로 생성해놓고 얘를 바꾸지않고 변경점없이 쓰기위해서 아래줄을 쓴다.
				Class commandClass = Class.forName(className);	// 해당 문자열을 클래스로 만든다. 메모리에 올려주는 역할을 한다.
				Object commandInstance = commandClass.newInstance();//해당클래스의 객체를 생성
				// 줄은 앞으로는 안쓰겠다 다른 메서드를 찾아라 라는 의미다.
				commandMap.put(command, commandInstance);		// Map객체인 commandMap에 객체 저장
				//		String으로 들어가고	Object로 들어간다.
			} catch (Exception e) {
				throw new ServletException(e);
			}
	    }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestServletPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestServletPro(request, response);
	}
	
	//사용자의 요청을 분석해서 해당 작업을 처리
	protected void requestServletPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		String command = request.getRequestURI();	// 프로젝트 + 파일경로
		System.out.println("1. command => "+command);	// /och16/list.do
		command = command.substring(request.getContextPath().length()); //command를 substring하겠다(request의 path의 length 만큼의 길이)
		System.out.println("2. command substring => "+command);	// /list.do
		try {
			com = (CommandProcess)commandMap.get(command);// service.ListAction의 instance값을 받는다.
			System.out.println("command => "+command);	// /och16/com
			System.out.println("com => "+com);			// /och16/com 객체니까 hash코드 주소다.
			System.out.println("(String)com => "+com.toString());
			view = com.requestPro(request, response);	// ListAction에 requestPro
			System.out.println("view => "+view);		// /och16/com
			
			 
		} catch (Exception e) {
			throw new ServletException(e);
		}
		// Ajax String을 포함하고 있으면
		if(command.contains("ajaxIdCheck")) {
			System.out.println("ajax String -> "+command);	// /och16/list.do
			//text 있다면
			int memberCnt = (int)request.getAttribute("memberCnt");
			String memberCntMessage = "";
			System.out.println("memberCnt -> "+memberCnt);
			if (memberCnt > 0 ) memberCntMessage = "이미존재하는 사용자입니다 다른 유저ID선택해주세요";
			else  memberCntMessage = "가능한 사용자입니다";
			PrintWriter pw = response.getWriter();
			pw.write(memberCntMessage);
			pw.flush();
			// ajax가 아니니까 이걸로 화면으로 보내준다.
		}else if (command.contains("ajaxLocation")){  //  AjaxList를 포함하고 있으면
		    request.setCharacterEncoding("utf-8"); 
		    response.setCharacterEncoding("utf-8");
		    SportsDao sd = SportsDao.getInstance();
		    Sports sp = new Sports();
		    System.out.println("ajax2");
			String list =  (String)request.getAttribute("list");
			System.out.println("list->"+list);
			System.out.println("test2");
			try {
				sp = sd.sportListSelect(list);
				System.out.println("sp.getexname->"+sp.getExname());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			String writer = null;
			if(sp.getPictureurl() != null) {
				writer = "<img src=images/"+sp.getPictureurl()+" class='sportsImage'><br>"+
						"<div class='context'>"+
								"<input type='hidden' id='user_id' value='${user_id}>"+
								"<input type='hidden' id='sport_type' value"+sp.getSport_type()+">"+
							"<span id='exname'>"+sp.getExname()+"</span><br>"+
							"<span id='mapwritecolor'>위치 : "+sp.getLoc()+"<br>"+
								"전화번호: "+sp.getTel()+"<br>"+
								"영업시간 : "+sp.getTime()+"<br>"+
								"<a href='"+sp.getHomeurl()+"'>사이트로 연결</a><p>"+
								"소개 <br> "+sp.getExcontext()+"<br></span>";
			}
			else if(sp.getPictureurl() == null) {
				writer = "<img src=images/null.gif class='nonSportsImage'><br>"+
						"<div class='context'>"+
							"<div class='checkcnt'></div>"+
								"<input type='hidden' id='user_id' value='${user_id}>"+
								"<input type='hidden' id='sport_type' value"+sp.getSport_type()+">"+
							"<span id='exname'>"+sp.getExname()+"</span><br>"+
							"<span id='mapwritecolor'>위치 : "+sp.getLoc()+"<br>"+
							"전화번호: "+sp.getTel()+"<br>"+
							"영업시간 : "+sp.getTime()+"<br>"+
							"<a id='site' href='"+sp.getHomeurl()+"'>사이트로 연결</a><p>"+
							"소개 <br> "+sp.getExcontext()+"<br></span>";
			}
			
			System.out.println("controller -> "+writer);
			PrintWriter pw = response.getWriter();
			pw.write(writer);
			
			pw.flush();
		}
		else if(command.contains("imgcheck")) {
            System.out.println("imgcheck -> "+command);    // /och16/list.do

            //text 있다면
            String like = (String) request.getAttribute("like");
            PrintWriter pw = response.getWriter();
            pw.write(like);
            pw.flush();
		}
		else if(command.contains("ajaxSportsWrite")) {
			System.out.println("ajaxSportsWrite -> "+command);
			
			//text 있다면
			String result = (String)request.getAttribute("result");
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
			
		} 
		// 일반적인 경우 
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
		
	}
}
