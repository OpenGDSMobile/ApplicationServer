package com.openGDSMobileApplicationServer.AttributeTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AttributeTableServiceImp implements AttributeTableService {


	@Autowired
	@Qualifier("attrDAO")
	AttributeTableDAO at; 
	
	@Override
	public String createAttr(String name) {
		
		
		at.selectAttr(name);
		return null;
	}

}
