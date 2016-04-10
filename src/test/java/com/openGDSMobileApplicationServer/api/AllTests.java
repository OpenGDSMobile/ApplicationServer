package com.openGDSMobileApplicationServer.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttrTablesRestControllerTest.class,
		GeoJsonRestControllerTest.class,
		GeoServerManagerRestControllerTest.class,
		PublicDataRestControllerTest.class,
		RealtimeTableRestControllerTest.class,
		SeoulOpenDataRestControllerTest.class })
public class AllTests {

}
