package com.openGDSMobileApplicationServer.PublicData;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class SeoulOpenDataServiceImpTest {

	@Test
	public void jriTest() {
		//fail("Not yet implemented");
		//{envType=PM10, timeValue=01:00, dateValue=2015-01-01, serviceName=TimeAverageAirQuality, keyValue=6473565a72696e7438326262524174}
		System.out.println("Test");
		String[] Rargs = {"--vanilla"};
		URL url = getClass().getResource("/rScript/testR.R");
		Rengine rengine = new Rengine(Rargs, false, null);
		
		rengine.eval("source('"+ url.getPath() + "')");
		//REXP rn = rengine.eval("as.integer(a<-testR())");
		//REXP rn = rengine.eval("a<-mbaFunc())");
		rengine.eval("sink('" + url.getPath() + "/jpgList.xml')");
		rengine.eval("cat('<?xml version=\"1.0\" encoding='utf-8' ?>\n')");
		rengine.eval("sink()");
		//System.out.println(rn);
		rengine.end();
		
	}

}
