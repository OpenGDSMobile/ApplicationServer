package com.openGDSMobileApplicationServer.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageMappingController {
	
	@RequestMapping("/demoPage.do")
	public String demoPage(){
		
		return "demoClient/index";
	}
	@RequestMapping("/APIPage.do")
	public String APIPage(){
		
		return "APIDoc/index";
	}
}
