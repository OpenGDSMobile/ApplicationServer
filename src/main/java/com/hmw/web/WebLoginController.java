package com.hmw.web;  
   

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils; 
import org.springframework.web.bind.annotation.RequestMapping; 
 
import com.hmw.ums.User;
import com.hmw.ums.UserService; 
 

@Controller
public class WebLoginController {
	 
	@Autowired
	UserService ser;  
	
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
