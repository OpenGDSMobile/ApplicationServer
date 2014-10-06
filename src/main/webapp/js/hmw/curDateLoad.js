var date = {};
var cur_date = new Date();
date.getYear = function(){	return cur_date.getFullYear();	};
date.getDate = function(){	return cur_date.getDate();		};
date.getMonth= function(){	return cur_date.getMonth();		};
date.getHour = function(){
	var hours;
	var minute = cur_date.getMinutes();
		if(minute < 30){
			if(cur_date.getHours()==0){
				date = leadingZeros(cur_date.getDate()-1,2);
				hours = 23;
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
date.getYYYYMMDD = function(){	
	var year = cur_date.getFullYear();
	var month = leadingZeros(cur_date.getMonth()+1,2);
	var date = leadingZeros(cur_date.getDate(),2);
	return year+month+date;
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