package com.hmw.airQuality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Repository;

@Repository("airQualityData") 
public class AirQualityDataDAOImp implements AirQualityDataDAO {

	@Override
	public void createMap() {
		String aqmHome = "/var/www/html/aqm";
		PrintWriter writer;
		
		try {
			File aqmFolder = new File(aqmHome);
			if (aqmFolder.exists() && aqmFolder.isDirectory()) {
			   System.out.println("aqm directory exists");
			}
			else{
				System.out.println("aqm directory not exists");
				aqmFolder.mkdir();
			}
			
			writer = new PrintWriter(aqmHome +"/gSource.csv", "UTF-8");
			writer.println("Easting,Northing,Elevation\n");
			
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
