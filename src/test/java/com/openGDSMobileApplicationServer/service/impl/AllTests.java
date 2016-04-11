package com.openGDSMobileApplicationServer.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttributeTableServiceImpTest.class, GeoJsonDAOTest.class, GeoJsonServiceImpTest.class,
		GeoServerManagerDAOTest.class, GeoServerManagerServiceImpTest.class, OpenGDSMobileTableDAOTest.class,
		PublicDataPortalDAOTest.class, PublicDataPortalServiceImpTest.class, 
		/*RealtimeInfoTableServiceImpTest.class,*/
		SeoulOpenDataDAOTest.class, SeoulOpenDataServiceImpTest.class })
public class AllTests {

}
