hmw.seoulOpenData = {}; 

hmw.seoulOpenData.request = {
		
};
hmw.seoulOpenData.env = {
	key : "6473565a72696e7438326262524174",
	colorRange : ["#4C4Cff","#9999FF","#4cff4c","#99ff99","#FFFF00","#FFFF99","#FF9900","#FF0000"],
	PM10Range : [15,30,55,80,100,120,200],
	PM25Range : [15,30,55,80,100,120,200],
	CORange : [1,2,5.5,9,10.5,12,15],
	NO2Range : [0.015,0.03,0.05,0.06,0.1045,0.15,0.2],
	SO2Range : [0.01,0.02,0.035,0.05,0.075,0.1,0.15],
	O3Range : [0.02,0.04,0.06,0.08,0.10,0.12,0.3],
	dataLoad : function(){
		//late....
	},
	/**
	 * SeoupOpenData Environment Data Division xData,yData 
	 * parameter : JSON data
	 */
	xydivision: function(data, envType){  
		var xyAxis = new Array();
			xyAxis[0] = new Array();
			xyAxis[1] = new Array();
		var row = data.row;
		$.each(row, function(idx){ 
			xyAxis[0].push(row[idx][envType]);
			xyAxis[1].push(row[idx]["MSRSTE_NM"]);
		});
		return xyAxis;
	}
};
