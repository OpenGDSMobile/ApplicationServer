/*
 * OpenGDS Mobile JavaScript Library  
 * Released under the MIT license
 */
var openGDSM = {};
	openGDSM.wmsMapUI = {};
var date = {};
var cur_date = new Date(); 
(function(){ 
	/**
	 * base Map Setting
	 * Parameter : divName -> Map div Name, mapStyle -> osm , vworld
	 */
	openGDSM.baseMap = function(divName, mapStyle){ 
		var mapType=null;
		if(mapStyle =='osm'){	
			mapType = new ol.source.OSM();		
		}
		else if(mapStyle=='vworld'){ 
			mapType = openGDSM.vWorld.TMS();
		} 
		//OpenLayers 3  
		Map.createBaseMap(divName, mapType); 
	};   
	/**  // F65FC751-4918-3760-9218-318D5E3577E0   //60
	 * Vworld WMS API User Interface 
	 * Parameter : divName -> UI div Name, apiKey->Vworld APIKey
	 */
	openGDSM.wmsMapUI.vworld = function(divName, apiKey){
		makeData = function(apiKey, chkName){
			var selectedData = "";
			var vworldChk = $("input[name='vworldWMS']:checkbox");
			vworldChk.each(function(i){ 
				if($(this).is(":checked")){
					selectedData+=$(this).attr("value");
					selectedData+=","; 
				}
			});
			selectedData = selectedData.slice(0,-1);
			openGDSM.vworld.wmsAPI(Map.map, apiKey, selectedData);
		};
		apiKey = "9E21E5EE-67D4-36B9-85BB-E153321EEE65";
		var rootDiv = $('#'+divName);
		var html = 'Select Max 5<br><fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
		var styles = ['LT_C_UQ111','LT_C_UQ112','LT_C_UQ113','LT_C_UQ114','LT_C_UQ121'];
		var stylesText = ['도시지역','관리지역','농립지역','자연환경보전지역','경관지구']; 

		for(var i=0; i<styles.length; i++){
			html += '<input type="checkbox" name="vworldWMS" class="custom" '+
					' id="id-'+styles[i]+'" value="'+styles[i]+'" />'+
					'<label for="id-'+styles[i]+'">'+stylesText[i]+'</label>';
			if(i!=0 && i%3==0){
				html+='</fieldset>'+
					  '<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
			}
		} 
		html += '</fieldset>'+
		        '<a href="#" id=wmsButton data-role="button" '+
		        'onclick=makeData("'+apiKey+'","vworldWMS")>지도 추가</a>';
		rootDiv.html(html);
		rootDiv.trigger("create");  
	};  
	
	/**
	 * Seoul Pulbic OpenData User Interface
	 */ 
	//TimeAverageAirQuality
	 openGDSM.seoulPublicUI = {
			 divName : '',
			 apiKey : '',
			 dateChk : false,
			 timeChk : false,
			 visualTypeRadioBtn : function(rootDiv){
				var html = '<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
				var arr = ['chart','map','chartMap'];
				var arrText = ['차트','맵','차트&맵'];
				for(var i=0; i<arr.length; i++){ 
						html += '<input type="radio" name="visradio" class="custom" '+
								' id="id-'+arr[i]+'" value="'+arr[i]+'"/>'+
								'<label for="id-'+arr[i]+'">'+arrText[i]+'</label>';
				} 
				html += '</fieldset>';		rootDiv.append(html);
			 },
			 inputDate : function(rootDiv){
				var html =  '<label for="dateValue">날짜 : (금일 날짜로 기본 세팅)</label>'+
							'<input type="date" id="dateValue"/>';
				rootDiv.append(html);
				this.dateChk = true; 
			 },
			 inputTime : function(rootDiv){
				var html =  '<label for="timeValue">시간 : (최신 데이터 시간)</label>'+
							 '<input type="time" id="timeValue">';
				rootDiv.append(html);
				this.timeChk = true;
			 },
			 inputValueSetting : function(){ 
				if(this.dateChk) $("#dateValue").attr('value',date.getYYYYMMDD("-"));
				if(this.timeChk) $("#timeValue").attr('value',date.getHour()+":00");
			 },
			 processBtn : function(rootDiv,serviceName){
					var html = '<a href="#" data-role="button" data-serivce="'+serviceName+'" '+ 
					 			'onclick="openGDSM.seoulPublicUI.makeData($(this))">세팅 완료/값 불러오기</a>';
					rootDiv.append(html);
			 },  			  
			 makeData : function(obj){    
				var visType="", envType="";
				var visRadio = $("input[name='visradio']:radio");
				var envRadio = $("input[name='envradio']:radio");
				visRadio.each(function(i){ 
					if($(this).is(":checked")){
						visType=$(this).val(); 
					}
				});
				envRadio.each(function(i){ 
					if($(this).is(":checked")){
						envType=$(this).val(); 
					}
				}); 
				$('#'+this.divName).popup("close");
				openGDSM.seoulOpenData.env.dataLoad(
						obj.attr("data-serivce"),
						this.apiKey,
						$("#dateValue").val(),
						$("#timeValue").val(), visType, envType); 
			 },
			 /// Services...
			 environment : function(divName, apiKey){ 
				$('#'+divName).empty();
				this.divName = divName;
				this.dateChk = false, this.timeChk = false;
				this.apiKey = '6473565a72696e7438326262524174'; 
				var rootDiv = $('#'+divName);
				this.visualTypeRadioBtn(rootDiv);
				this.inputDate(rootDiv);
				this.inputTime(rootDiv);
				
				var html = '<label for="envValue">환경정보:</label>'+
						   '<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
				var envType = ['PM10','PM25','SO2','O3','NO2','CO'];
				for(var i=0; i<envType.length; i++){
					html += '<input type="radio" name="envradio" class="custom" '+
					' id="id-'+envType[i]+'" value="'+envType[i]+'"/>'+
					'<label for="id-'+envType[i]+'">'+envType[i]+'</label>';
					if(i!=0 && (i+1)%3==0){
						html+='</fieldset>'+
							  '<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">';
					}
				}
				html += '</fieldset>';		
				rootDiv.append(html);
				this.processBtn(rootDiv, "TimeAverageAirQuality");
				rootDiv.trigger("create");
				this.inputValueSetting();
			 }
	 }; 
	 
	 
	 
	///////////////////////////////////////////////////////
	openGDSM.geoServerProcess = function(obj){
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
	openGDSM.publicOpenData = function(obj,state,data){
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
					var colorRange =openGDSM.seoulOpenData.env.colorRange; 
					var xyData=openGDSM.seoulOpenData.env.xydivision(dataList, envType);
					if(envType=="PM10")
						envType = openGDSM.seoulOpenData.env.PM10Range;
					else if(envType=="PM25")
						envType = openGDSM.seoulOpenData.env.PM25Range;
					else if(envType=="CO")
						envType = openGDSM.seoulOpenData.env.CORange;
					else if(envType=="NO2")
						envType = openGDSM.seoulOpenData.env.NO2Range;
					else if(envType=="SO2")
						envType = openGDSM.seoulOpenData.env.SO2Range;
					else if(envType=="O3")
						envType = openGDSM.seoulOpenData.env.O3Range;
					
					openGDSM.d3.barchart('d3View',xyData,colorRange,envType);	
				} 

				if($(obj).attr('data-value')=="RealtimeRoadsideStation"){
					var envType = $(':radio[name="env"]:checked').val();
					var colorRange =openGDSM.seoulOpenData.env.colorRange; 
					var xyData=openGDSM.seoulOpenData.env.xydivision(dataList, envType);
					if(envType=="PM10")
						envType = openGDSM.seoulOpenData.env.PM10Range;
					else if(envType=="PM25")
						envType = openGDSM.seoulOpenData.env.PM25Range;
					else if(envType=="CO")
						envType = openGDSM.seoulOpenData.env.CORange;
					else if(envType=="NO2")
						envType = openGDSM.seoulOpenData.env.NO2Range;
					else if(envType=="SO2")
						envType = openGDSM.seoulOpenData.env.SO2Range;
					else if(envType=="O3")
						envType = openGDSM.seoulOpenData.env.O3Range;
					
					openGDSM.d3.barchart('d3View',xyData,colorRange,envType); 
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
	ajaxNetwork = function(obj, data){
		$.ajax({
			type:'POST',
			url:$(obj).attr('data-name')+'.do',
			data: JSON.stringify(data), 
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			success:function(msg){ 
				openGDSM.publicOpenData(obj,"jsondata",JSON.parse(msg.data));
				//openGDSM.geoServertest(obj,JSON.parse(msg.data));
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