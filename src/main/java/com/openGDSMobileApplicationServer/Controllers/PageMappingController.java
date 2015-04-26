package com.openGDSMobileApplicationServer.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageMappingController {
	
	@RequestMapping("/demoPage.do")
	public String demoPage(){
		
		return "demoClient/index";
	}
	@RequestMapping("/openData.do")
	public String openData(){
		
		return "WEB-INF/openData/index.html";
	}

}
