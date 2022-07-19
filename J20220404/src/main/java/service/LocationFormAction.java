package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dao.Sports;
import dao.SportsDao;

public class LocationFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SportsDao sd = SportsDao.getInstance();
		System.out.println("LocationFormAction Service Start...");
		String clientId = "네이버 API를 사용하기 위한 클라이언트 ID 입력";  //clientId 
        String clientSecret = "네이버 API를 사용하기 위한 클라이언트 비밀번호 입력";  //clientSecret 
		// 게시판 갯수
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
//				기본 sql관련 코드
				user_id = request.getSession().getAttribute("user_id").toString();
				String sportType = request.getParameter("sportType");
				if(sportType == null || sportType == "")
					sportType = "1";
				System.out.println("sportType => "+sportType);
				int totCnt = sd.getTotalCnt(sportType);
				if(user_id == null) {
					user_id = request.getSession().getAttribute("admin_id").toString();
				}
				List<Sports> list = sd.sportList(sportType, user_id);
				for(int i=0; i<list.size(); i++) {
					System.out.println(list.get(i).getLoc().toString());
				}
				Map<Integer, String> Lat = new HashMap<Integer, String>();
				Map<Integer, String> Lng = new HashMap<Integer, String>();
				for(int i=0; i<list.size(); i++) {
//					geocoder를 사용해 값 추출하는 라인
					String address = list.get(i).getLoc().toString();
					System.out.println(list.get(i).getLoc().toString());
					String addr = URLEncoder.encode(address, "UTF-8");  //주소입력
			        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr; //json
			    	URL url = new URL(apiURL);
			        HttpURLConnection con = (HttpURLConnection)url.openConnection();
			        con.setRequestMethod("GET");
			        con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			        con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			        int responseCode = con.getResponseCode();
			        BufferedReader br;
			        if(responseCode==200) {
			            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			        } else {
			            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			        }
			        String inputLine;
			        StringBuffer inputLinebuffer = new StringBuffer();
			        while ((inputLine = br.readLine()) != null) {
			        	JSONParser jsonParser = new JSONParser();
			        	JSONObject jsonObject = (JSONObject)jsonParser.parse(inputLine);
			        	JSONArray jsonArray = (JSONArray)jsonObject.get("addresses");
			        	for(int j=0; j<jsonArray.size(); j++){
			        		System.out.println("test01");
			                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
			                JSONObject jsonObject2 = (JSONObject) jsonArray.get(j);
			                System.out.println("test02");
			                //JSON name으로 추출
			                System.out.println("json: roadAddress==>"+jsonObject2.get("roadAddress"));
			                System.out.println("json: x==>"+jsonObject2.get("x"));
			                System.out.println("json: y==>"+jsonObject2.get("y"));
			                Lat.put(i, jsonObject2.get("y").toString());
			                Lng.put(i, jsonObject2.get("x").toString());
			            }
			        	inputLinebuffer.append(inputLine);
			        	System.out.println(Lat.get(i));
			        	System.out.println(Lng.get(i));
			        }
			        br.close();
			        
				}
				
				request.setAttribute("list", list.get(0));
				request.setAttribute("list2", list);
				request.setAttribute("listsize", list.size());
				
				request.setAttribute("Lat", Lat);
				request.setAttribute("Lng", Lng);
				request.setAttribute("totCnt", totCnt);
			} catch (SQLException e) {
				System.out.println("LocationFormAction SQLException -> "+e.getMessage());
			} catch (Exception e) {
				System.out.println("LocationFormAction Exception -> "+e.getMessage());
			}

			return "locationForm.jsp";
		}
	}
}
