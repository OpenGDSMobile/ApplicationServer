package com.hmw.web; 
 
  
  
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody; 

import com.hmw.geomanager.GeoManagerService;
import com.hmw.ums.User;
import com.hmw.ums.UserService;
import com.hmw.util.Util;
 

@Controller
public class WebLoginController {
	 
	@Autowired
	UserService ser;
	
	@Autowired
	GeoManagerService geomanager;
	 
	
	 
	
	@RequestMapping(headers="Content-Type=application/json", value="/createWorkspace.do",
					method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Map<String, Object> workspaceCreate(@RequestBody String JSONData){
		
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			//Map<String,Object> data = Util.convertJsonToObject(JSONData);
			//boolean result = geomanager.createWorkspace(data.get("name").toString());
			
			message.put("result", "OK");
			message.put("message", null);
		//	message.put("data", result);
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	} 
	
	
	@RequestMapping(headers="Content-Type=application/json", value="/loadVector.do",
			method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> loadVector(@RequestBody String JSONData){
		//System.out.println(geomanager.requestLoadVector());
		geomanager.requestLoadVector("korea", "");
		/*
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			Map<String,Object> data = Util.convertJsonToObject(JSONData);
		//	geomanager.requestLoadVector("korea", data.get("name").toString());
			message.put("result", "OK");
			message.put("message", null);
		//	message.put("data", geomanager.requestLoadVector("korea", data.get("name").toString() ));
			return message;
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
		//return geomanager.requestLoadVector();
		 * 
		 */
		return null;
	}
	
	
	
	@RequestMapping("/index.do")
	public String indexPage(Model m){
		return "redirect:/index.jsp";
	}

	@RequestMapping("/logout.do")
	public String logOut(Model m, HttpSession session){
		//System.out.println(session.getAttribute("userid")+"-> userId");
		session.removeAttribute("userid");
		return "redirect:/index.jsp";
	}
	@RequestMapping("/login.do")
	public String map(String userId, String passwd, HttpSession session, Model m, 
			HttpServletRequest request,HttpServletResponse response){
		User result=null;
		//System.out.println(session.getAttribute("userid")+"-> userId");
		
		String origin = request.getHeader("Origin");
		if(StringUtils.hasLength(origin)){
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Credentials", "true");
//			System.out.println(response.toString());
		}
		
		
		if(session.getAttribute("userid") ==null){
			result = ser.login(userId, passwd);
			if(result !=null){
				session.setAttribute("userid", userId);
			}
		
		}
		
		if(result !=null || session.getAttribute("userid")!=null){
			return "mapView";
		}
		else return "redirect:/index.jsp";
	}
	
	@RequestMapping("/join_page.do")
	public String joinPage(Model m){
		return "join";
	}
	@RequestMapping("/join_exe.do")
	public String joinExec(User u, Model m){
	//	System.out.println("Controller:"+u.toString());
		boolean result = ser.insert(u);
		if(result)
			return "redirect:/index.jsp";
		else
			return "join";
	}

}
