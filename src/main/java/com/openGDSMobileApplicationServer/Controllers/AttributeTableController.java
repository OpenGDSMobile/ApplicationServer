package com.openGDSMobileApplicationServer.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openGDSMobileApplicationServer.AttributeTable.AttributeTableService;


@Controller
public class AttributeTableController {
	
	@Autowired
	AttributeTableService ats;
	
	
	@RequestMapping("/attrTable.do")
	public String attrTable(Model m){
		//return "redirect:/main.jsp";
		System.out.println("test");
		ats.createAttr("seoul_env_position");
		return "ok";
	}
	

}
