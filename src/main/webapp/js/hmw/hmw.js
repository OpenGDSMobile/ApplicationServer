/*
 * OpenGDS Mobile JavaScript Library  
 * Released under the MIT license
 */
var hmw = {};
	hmw.wmsMap = {};
var date = {};
var cur_date = new Date(); 
(function(){ 
	/**
	 * 
	 */
	hmw.baseMap = function(div, mapStyle){ 
		var mapType=null;
		if(mapStyle =='osm'){	
			mapType = new ol.source.OSM();		
		}
		else if(mapStyle=='vworld'){
			mapType = new ol.source.XYZ(({
  				url : "http://xdworld.vworld.kr:8080/2d/Base/201310/{z}/{x}/{y}.png"
  			}));
		} 
		//OpenLayers 3 
		Map.createBaseMap(div, mapType); 
	};  

	/**
	 * 
	 */
	hmw.wmsMap.vworld = function(divName, apiKey){
		var rootDiv = $('#'+divName);
		var html = 'Select Max 5<br><fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
		var styles = ['LT_C_UQ111','LT_C_UQ112','LT_C_UQ113','LT_C_UQ114','LT_C_UQ121'];
		var stylesText = ['도시지역','관리지역','농립지역','자연환경보전지역','자연환경보전지역11']; 
		for(var i=0; i<styles.length; i++){
			html += '<input type="checkbox" name="vworldWMS" class="custom" '+
					'id="id-'+styles[i]+'" value="'+styles[i]+'" />'+
					'<label for="id-'+styles[i]+'">'+stylesText[i]+'</label>';
			if(i!=0 && i%3==0){
				html+='</fieldset>'+
					  '<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
			}
		} 
		html += '</fieldset>'+
		        '<a href="#" id=wmsButton data-role="button" '+
		        'onclick=Map.addMap.wmsLayer("'+apiKey+'","vworldWMS")>지도 추가</a>';
		rootDiv.html(html);
		rootDiv.trigger("create");  
	}; 
	/*
	hmw.wmsMap.vworld = function(wmsStyle){
		wmsStyle = $(wmsStyle).attr('data-layer'); 
		Map.addMap.wmsLayer(wmsStyle);
	};
	*/	
	///////////////////////////////////////////////////////
	hmw.geoServerProcess = function(obj){
		console.log($(obj).attr('data-name')); 
		switch($(obj).attr('data-name')){
		case 'loadVector':
			var mapLayers = Map.map.getLayers(),
			obj_exist=false,
			name = $(obj).attr('data-id'),
			r = Math.floor(Math.random()*256),
			g = Math.floor(Math.random()*256),
			b = Math.floor(Math.random()*256); 
			mapLayers.forEach(function(data){
				if(data.get('title')==name){
					obj_exist = true;		
					Layer.removeLayer(data);
					return ;
				}
			}); 
			if(!obj_exist)	{
				Layer.createLayer(obj,'rgba('+r+','+g+','+b+',1.0)',1);
			//ajaxNetwork(obj,data);
			}
			
		break;
		case 'createWorkspace':
			//createkeyValueJsonString($(obj));
			ajaxNetwork(obj,data);
			break;
		
		}
		//ajaxNetwork(obj);
	}; 
	
	/** keyValue, DateValue, TimeValue -- data-key, data-value**/
	hmw.publicOpenData = function(obj,state,data){
		state = (typeof(state) !== 'undefined') ? state : "start";
		data = (typeof(data) !== 'undefined') ? data : null; 
		
		///// Checkbox Check!!
		var chkValues = ["keyValue","dateValue","timeValue"];
		var chkArray=[];
		chkArray.push($(obj));
		for(i in chkValues){
			if( $("#chk"+chkValues[i]).is(":checked") ){
				$('#'+chkValues[i]).attr('data-value',$('#'+chkValues[i]).attr('value'));
				chkArray.push($('#'+chkValues[i]));
			}
		}  
		//6473565a72696e7438326262524174    env
		//4b56506967696e7437317348694371	road

		switch($(obj).attr('data-name')){
		case 'SeoulpublicOpenData':
			if(state=="start"){  
				var jsonStringData = createkeyValueJsonString(chkArray);
				$('#'+$(obj).attr('data-popup')).popup('close');
				ajaxNetwork(obj,jsonStringData);
			}
			else if(state=="jsondata"){
				var dataList = data[ $(obj).attr('data-value') ]; // Service Name
			//	var keys = Object.keys(dataList);  

				$('#dataSelect').popup("open");
				
				
				if($(obj).attr('data-value')=="TimeAverageAirQuality"){
					var envType = $(':radio[name="env"]:checked').val();
					var colorRange =hmw.seoulOpenData.env.colorRange; 
					var xyData=hmw.seoulOpenData.env.xydivision(dataList, envType);
					if(envType=="PM10")
						envType = hmw.seoulOpenData.env.PM10Range;
					else if(envType=="PM25")
						envType = hmw.seoulOpenData.env.PM25Range;
					else if(envType=="CO")
						envType = hmw.seoulOpenData.env.CORange;
					else if(envType=="NO2")
						envType = hmw.seoulOpenData.env.NO2Range;
					else if(envType=="SO2")
						envType = hmw.seoulOpenData.env.SO2Range;
					else if(envType=="O3")
						envType = hmw.seoulOpenData.env.O3Range;
					
					hmw.d3.barchart('d3View',xyData,colorRange,envType);	
				} 

				if($(obj).attr('data-value')=="RealtimeRoadsideStation"){
					var envType = $(':radio[name="env"]:checked').val();
					var colorRange =hmw.seoulOpenData.env.colorRange; 
					var xyData=hmw.seoulOpenData.env.xydivision(dataList, envType);
					if(envType=="PM10")
						envType = hmw.seoulOpenData.env.PM10Range;
					else if(envType=="PM25")
						envType = hmw.seoulOpenData.env.PM25Range;
					else if(envType=="CO")
						envType = hmw.seoulOpenData.env.CORange;
					else if(envType=="NO2")
						envType = hmw.seoulOpenData.env.NO2Range;
					else if(envType=="SO2")
						envType = hmw.seoulOpenData.env.SO2Range;
					else if(envType=="O3")
						envType = hmw.seoulOpenData.env.O3Range;
					
					hmw.d3.barchart('d3View',xyData,colorRange,envType); 
				}
				
			} 
			else if(state=="tableView"){
				console.log($('#selectData').attr('value'));
			}
			else if(state=="d3View"){
				
			}
		break; 
		} 
	};
	
	createkeyValueJsonString = function(data){    
		var str = "{";
		for(var i in data){ 
			str= str + '"'+data[i].attr('data-key')+'":';
			str= str + '"'+data[i].attr('data-value'); 
			if((data.length-1)==i)	str +='"}';
			else						str +='",';
		}  
		return JSON.parse(str); 
	};//JSON {key:value} 
	/*
	hmw.geoServertest = function(obj,GeoJson){
		console.log($(obj).attr('data-id'));
		var vectorTemp = new ol.layer.Vector({
	    	title:$(obj).attr('data-id'),
	 	   	source: new ol.source.GeoJSON({
	 	   		projection: 'EPSG:4326',
	 	   		//url : "loadVector.do",
	 	   		url : GeoJson
	 	   		//url : "geoserver-GetFeature1.json"
	 	   	}), 
	 	   	style: new ol.style.Style({
	 		   stroke: new ol.style.Stroke({
	 			   color:"rgba(255,255,0,1.0)",
	 			   width:1
	 		   })
	 	   })
	    });
	    Map.map.addLayer(vectorTemp);
	};
	*/
	ajaxNetwork = function(obj, data){
		$.ajax({
			type:'POST',
			url:$(obj).attr('data-name')+'.do',
			data: JSON.stringify(data), 
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			success:function(msg){ 
				hmw.publicOpenData(obj,"jsondata",JSON.parse(msg.data));
				//hmw.geoServertest(obj,JSON.parse(msg.data));
			},
			error:function(){
				console.log("err");
			}
		}); 
	};

	
/**
 * getDate Module About Public Date 
 */	 
	date.getYear = function(){	return cur_date.getFullYear();	};
	date.getDate = function(){	return cur_date.getDate();		};
	date.getMonth= function(){	return cur_date.getMonth();		};
	date.getHour = function(){
		var hours;
		var minute = cur_date.getMinutes();
			if(minute < 30){
				if(cur_date.getHours()==0){  
					hours = leadingZeros(cur_date.getHours()+23,2);
				}else	hours = leadingZeros(cur_date.getHours()-1,2);
			}else{
				hours = leadingZeros(cur_date.getHours(),2); 
			} 
		return hours;
	};// 30 minute cut line  (30 up) +1 
	date.getMin = function(){	return cur_date.getMinutes();	}; 
	date.getYYYYMMDDHH = function(){
		var year = cur_date.getFullYear();
		var month = leadingZeros(cur_date.getMonth()+1,2);
		var date = leadingZeros(cur_date.getDate(),2);
		var minute = cur_date.getMinutes();
		var hours;
			if(minute < 30){
				if(cur_date.getHours()==0){
					date = leadingZeros(cur_date.getDate()-1,2);
					hours = 23;
				}else	hours = leadingZeros(cur_date.getHours()-1,2);
			}else{
				hours = leadingZeros(cur_date.getHours(),2); 
			} 
			minute = "00";
		return year+month+date+hours+minute;
	};
	date.getYYYYMMDD = function(plug){	
		var year = cur_date.getFullYear();
		var month = leadingZeros(cur_date.getMonth()+1,2);
		var date = leadingZeros(cur_date.getDate(),2);
		if(plug==null)
			return year+month+date;
		else
			return year+plug+month+plug+date;
	};

	leadingZeros = function(n,digits){
		var zero = '';
		n = n.toString();
		if (n.length < digits) {
			for (var i = 0; i < digits - n.length; i++)
				zero += '0';
		}
	  return zero + n;
	};	
	
})();