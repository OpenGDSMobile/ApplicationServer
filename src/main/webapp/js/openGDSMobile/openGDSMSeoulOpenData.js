openGDSM.seoulOpenData = { };  
openGDSM.seoulOpenData.env = {
	key : "6473565a72696e7438326262524174",
	serviceName : '',
	envType : '',
	visType : '',
	colorRange : ["#4C4Cff","#9999FF","#4cff4c","#99ff99","#FFFF00","#FFFF99","#FF9900","#FF0000"],
	PM10Range : [15,30,55,80,100,120,200],
	PM25Range : [15,30,55,80,100,120,200],
	CORange : [1,2,5.5,9,10.5,12,15],
	NO2Range : [0.015,0.03,0.05,0.06,0.1045,0.15,0.2],
	SO2Range : [0.01,0.02,0.035,0.05,0.075,0.1,0.15],
	O3Range : [0.02,0.04,0.06,0.08,0.10,0.12,0.3], 
	dataLoad : function(serviceName,apiKey,dateValue,timeValue, visType, envType){
		this.serviceName = serviceName;
		dateValue = (typeof(dateValue) !== 'undefined') ? dateValue : "";
		timeValue = (typeof(timeValue) !== 'undefined') ? timeValue : "";
		var data = '{"serviceName":"'+serviceName+'",'+
				   ' "keyValue":"'+apiKey+'",'+					
				   '"dateValue":'+'"'+dateValue+'",'+
				   '"timeValue":'+'"'+timeValue+'"}';
		data=JSON.parse(data);
		this.envType = envType;
		this.visType = visType;
		$.ajax({
			type:'POST',
			url:'EnvironmentSeoulData.do',
			data: JSON.stringify(data), 
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			success:function(msg){  
				if(visType=='chart')
					openGDSM.seoulOpenData.env.chartVisual(JSON.parse(msg.data));
				else if(visType=='map')
					openGDSM.seoulOpenData.env.mapVisual(JSON.parse(msg.data));
				else if(visType=='chartMap')
					openGDSM.seoulOpenData.env.chartMapVisual(JSON.parse(msg.data));
			},
			error:function(){
				console.log("err");
			}
		}); 
	}, 
	chartVisual : function(data){
		$('#dataSelect').popup("open");
		var envRange=[];  
		var xyData=this.xydivision(data[this.serviceName], this.envType, 'MSRSTE_NM');
		if(this.envType=="PM10")
			envRange = openGDSM.seoulOpenData.env.PM10Range;
		else if(this.envType=="PM25")
			envRange = openGDSM.seoulOpenData.env.PM25Range;
		else if(this.envType=="CO")
			envRange = openGDSM.seoulOpenData.env.CORange;
		else if(this.envType=="NO2")
			envRange = openGDSM.seoulOpenData.env.NO2Range;
		else if(this.envType=="SO2")
			envRange = openGDSM.seoulOpenData.env.SO2Range;
		else if(this.envType=="O3")
			envRange = openGDSM.seoulOpenData.env.O3Range;  
		openGDSM.d3.barchart('d3View',xyData,this.colorRange,envRange); 
		//향후 클라이언트에서 나누는것이 아닌 서버에서 모든 작업 후...
	},
	/**
	 * SeoupOpenData Environment Data OpenLayers Style
	 */ 
	mapVisual: function(data){ 
		
		var envRange=[];  
		var xyData=this.xydivision(data[this.serviceName], this.envType, 'MSRSTE_NM');
		if(this.envType=="PM10")
			envRange = openGDSM.seoulOpenData.env.PM10Range;
		else if(this.envType=="PM25")
			envRange = openGDSM.seoulOpenData.env.PM25Range;
		else if(this.envType=="CO")
			envRange = openGDSM.seoulOpenData.env.CORange;
		else if(this.envType=="NO2")
			envRange = openGDSM.seoulOpenData.env.NO2Range;
		else if(this.envType=="SO2")
			envRange = openGDSM.seoulOpenData.env.SO2Range;
		else if(this.envType=="O3")
			envRange = openGDSM.seoulOpenData.env.O3Range; 
		
		
		
	},
	chartMapVisual: function(data){
		console.log('map Chart');
		console.log(data);
	},
	/**
	 * SeoupOpenData Environment Data Division xData,yData 
	 * parameter : JSON data
	 * return : 2 dim x axis and y axis   [0]: x Axis, [1] : y Axis
	 */
	xydivision: function(data, envType, yType){  
		var xyAxis = new Array();
			xyAxis[0] = new Array();
			xyAxis[1] = new Array();
		var row = data.row;
		$.each(row, function(idx){ 
			xyAxis[0].push(row[idx][envType]);
			xyAxis[1].push(row[idx]["MSRSTE_NM"]);
		});
		return xyAxis;
	},
};
