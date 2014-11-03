package com.hmw.airQuality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository("airQualityData") 
public class AirQualityDataDAOImp implements AirQualityDataDAO {

	@Override
	public void createMap() {

		final String airEnvironmentJSON_filePath = "/var/www/html/aqm/201411021600.json";
		
		double[] seoulAirLocationLatitude  = {37.51754,  37.54573,	37.63788,  37.54465,  37.48736,  37.54798,	37.50373,  37.45239,  37.65878,  37.65420,  37.57578,  37.48296,  37.54975,  37.57670,	37.50457,  37.54323,  37.60674,  37.52182,	37.52342,  37.52607,  37.54090,  37.60983,	37.57204,  37.56427,  37.58485};
		double[] seoulAirLocationLongitute = {127.04747, 127.13666, 127.02886, 126.83516, 126.92710, 127.09267, 126.89032, 126.90834, 127.06850, 127.02909, 127.02887, 126.97300, 126.94558, 126.93786, 126.99450, 127.04193, 127.02728, 127.11650, 126.85871, 126.89723, 127.00465, 126.93485, 127.00502, 126.97468, 127.09402}; 
		
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
			writer.println("Easting,Northing,Elevation");
			
			FileReader reader = new FileReader(airEnvironmentJSON_filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONObject rootObject = (JSONObject) jsonObject.get("TimeAverageAirQuality");
			JSONArray rows= (JSONArray) rootObject.get("row");

			for(int i=0; i<rows.size(); i++){
				JSONObject tObj = (JSONObject) rows.get(i);
				//System.out.println("The PM10 " + tObj.get("MSRSTE_NM") + tObj.get("PM10"));
				writer.println(seoulAirLocationLongitute[i] + "," + seoulAirLocationLatitude[i] + "," + tObj.get("PM10"));
		    }
			
			writer.close();
			
			
			writer = new PrintWriter(aqmHome +"/gSource.vrt", "UTF-8");
			String writeString = "<OGRVRTDataSource>\n" + 
					      	     "  <OGRVRTLayer name=\"gSource\">\n" +
					      	     "    <SrcDataSource>" + aqmHome + "/gSource.csv</SrcDataSource>\n" +                     
					      	     "    <GeometryType>wkbPoint</GeometryType>\n"+		
					      	     "    <GeometryField encoding=\"PointFromColumns\" x=\"Easting\" y=\"Northing\" z=\"Elevation\"/>\n" +
					      	     "  </OGRVRTLayer>\n" +
					      	     "</OGRVRTDataSource>";
	        writer.println(writeString);
	        writer.close();
	                    
	        Runtime rt = Runtime.getRuntime();
	        
	        String e_grid = "gdal_grid -zfield \"Elevation\" -a_srs EPSG:4326 -txe 126.764 127.184  -tye 37.4285 37.7014 -of GTiff -l gSource " + aqmHome + "/gSource.vrt" + " " + aqmHome + "/gSource_init.tif";
	        Process p = rt.exec(new String[]{"bash","-c", e_grid});
	        p.waitFor();
	        
	        String e_relief = "gdaldem color-relief " + aqmHome +"/gSource_init.tif " + aqmHome + "/color/pm10_color_code.txt " + aqmHome + "/gSource_relief.tif";
	        p = rt.exec(new String[]{"bash","-c", e_relief});
	        p.waitFor();
	        
	        String e_warp = "gdalwarp -cutline " + aqmHome + "/shp/wgs.shp -overwrite -tr 10 10 -dstnodata -99999 -s_srs EPSG:4326 -t_srs EPSG:3857 " + aqmHome + "/gSource_relief.tif " + aqmHome + "/gSource.tif";   
	        p = rt.exec(new String[]{"bash","-c", e_warp});
	        p.waitFor();
	        
	        String e_translate = "gdal_translate -of JPEG " + aqmHome + "/gSource.tif " + aqmHome + "/gSource_result.jpg";
	        p = rt.exec(new String[]{"bash","-c", e_translate});
	        p.waitFor();
	        
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
