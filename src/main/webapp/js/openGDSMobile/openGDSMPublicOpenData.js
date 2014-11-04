openGDSM.publicOpenData = {
		urls : {
			airkorea : 'http://openapi.airkorea.or.kr/openapi/service/rest/'
		}
};  

openGDSM.publicOpenData.env = {
		visType :'',
		envType :'',
		areaType :'',
		mapLayer : '',
		dataLoad : function(provider,serviceName,apiKey,visType,envType,areaType,mapLayer){
			mapLayer = (typeof(mapLayer) !== 'undefined') ? mapLayer : "";
			console.log(provider+' '+serviceName+' '+apiKey+' '+visType+' '+envType+' '+areaType+' '+mapLayer);
			this.visType = visType;
			this.envType = envType;
			this.areaType = areaType;
			this.mapLayer = mapLayer;
			var data = '{"serviceName":"'+serviceName+'",'+
			   			'"keyValue":"'+apiKey+'",'+	
			   			'"areaType":"'+encodeURIComponent(areaType)+'",'+	
			   			'"envType":"'+envType+'",'+	
			   			'"provider":'+'"'+provider+'"}';
				data=JSON.parse(data);
		$.ajax({
			type:'POST',
			url:'EnvironmentPublicData.do',
			data: JSON.stringify(data), 
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			success:function(msg){  
			/*	if(visType=='chart')
					openGDSM.seoulOpenData.env.chartVisual(JSON.parse(msg.data));
				else if(visType=='map')
					openGDSM.seoulOpenData.env.mapVisual(JSON.parse(msg.data));
				else if(visType=='chartMap')
					openGDSM.seoulOpenData.env.chartMapVisual(JSON.parse(msg.data));
			*/
				console.log(JSON.parse(msg.data));
			},
			error:function(){
				console.log("err");
			}
		}); 
		}
};