$.ajax({
		type:'POST',
		url:url,/* 
		data : {
			service : 'wfs',
			version : '1.1.0',
			request : 'getJson',
			typeNames : 'opengds:Seoul_si',
			outputFormat : 'text/javascript'
		},*/
		dataType : 'jsonp',
	//	jsonpCallback : loadFeatures,
		success:function(msg){
			console.log("data");
			console.log(msg);
			//hmw.geoServertest(obj,JSON.parse(msg.data));
		},
		error:function(xhr, status , err){
			console.log(status +';'+err);
		}
	});


	/*
	getJSONArray = function(obj, str){
		console.log(obj.length);
		for(var i=0; i<obj.length; i++){ 
			console.log(JSON.stringify(obj[i]));
			//str = str + getJSONObject(obj[i],str);
			//str = str + "<br>";
		}
		return str;
	};
	getJSONObject = function(obj, str){ 
		var keys = Object.keys(obj);
		for(var i in keys){ 
		//	if(obj[keys[i]].constructor== Array)
		//		getJSONArray(obj,str);
		//	else
				str=str+keys[i]+":"+obj[keys[i]]+", ";
		}
		console.log(str);
		return str;
		
		 * 
			if(obj[keys[i]].constructor == Array)
				console.log("eeee");
			else
		 
	}; 
	*/



			/*	var jsonStringData = createkeyValueJsonString(
						$('#serviceName'),
						$('#keyValue'),
						$('#dateValue'));*/