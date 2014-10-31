<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>    
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
 	
	<!-- 	jQuery -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery-1.9.1.min.js"></script>
	
	<!-- jQuery Mobile -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery.mobile-1.3.2.min.js"></script>
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css">
	 
	<!-- eGov Mobile -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/EgovMobile-1.3.2.css">
	
	<!-- Proj4js -->
	<script type="text/javascript" src="js/proj4js/2.2.2/proj4.js"></script>
	<script type="text/javascript" src="js/proj4js/2.2.2/EPSGdefs.js"></script>  
	
	<!-- OpenLayers 3 -->
	<script type="text/javascript" src="js/ol3/ol.js"></script>
	<link type="text/css" rel="stylesheet" href="css/ol3/ol.css">  
	
	<!-- D3.js -->
	<script type="text/javascript" src="js/d3/d3.js"></script>
	<script type="text/javascript" src="topojson.v1.min.js"></script>
	
	<script type="text/javascript" src="js/hmw/MapConfig.js"></script>
	<script type="text/javascript" src="js/hmw/MapVectorLayers.js"></script>
	<script type="text/javascript" src="js/hmw/MapSetting.js"></script>
	<script type="text/javascript" src="js/hmw/MapGui.js"></script>
	<script type="text/javascript" src="js/hmw/EventScript.js"></script>
	 
	<script type="text/javascript" src="js/hmw/hmw.js"></script>
	<script type="text/javascript" src="js/hmw/hmwSeoulOpenData.js"></script>
	<script type="text/javascript" src="js/hmw/hmwD3.js"></script>
	 
	
	<title>Map Select</title>
	<script> 
	beforeProcess = {
		popupSize: function(obj,width,height){
			width = (typeof(width) !== 'undefined') ? width : $(window).width()-50;
			height = (typeof(height) !== 'undefined') ? height : "300px"; 
			$(obj).css("width",width/2);
			$(obj).css("height",height);
			$(obj).css("overflow-y","auto");
			$(obj).css("overflow-x","hidden"); 
		},
		popupOpen: function(obj){ 
			$('#serviceName').attr('data-value',$(obj).attr('data-value'));	
		}
	};
	styleChange = function(obj){
		$('#wmsButton').attr('data-layer',$(obj).attr('value')); 
	};
	$(document).ready(function(){ 
 		$("#dateValue").attr('value',date.getYYYYMMDD("-"));
		$("#timeValue").attr('value',date.getHour()+":00");
		$("#d3View").attr('width',$(window).width()-100);
		beforeProcess.popupSize("#dataSelect");
		beforeProcess.popupSize("#vworldList","300px"); 
	});
	</script>
	
</head>
<body>  
<div data-role="page" id="mapview">
	<!-- Vworld WMS --><!--  data-dismissible="false" -->
		<div data-role="popup" id="vworldList"  data-overlay-theme="a" style="padding: 15px">
			<fieldset id="vworldWMSGroup111" data-role="controlgroup" data-type="horizontal" class="egov-align-center">
				<input type="checkbox" class="custom" id="1" value="1" name="1">
				<label for="1">eee</label>
				<input type="checkbox" class="custom" id="2" value="1" name="1">
				<label for="2">eqqqee</label>
			</fieldset>
		</div> 
	<!-- Data Table 아직... --> 
		<div data-role="panel" data-display="overlay" data-position="right" id="publicDataView">
			<table id="dataTable">
			</table>	
		</div>
	<!-- Public Data Select -->
		<div data-role="popup" id="dataSelect" data-overlay-theme="a">
		  <div id="range">
              			<table style="margin:0 auto">
              				<tr>
              					<td style="background:#4c4cff; margin:0; padding:0">　</td>
              					<td style="background:#9999ff; margin:0; padding:0">　</td>
              					<td>Good</td>
              					<td style="background:#4CFF4C; margin:0; padding:0">　</td>
              					<td style="background:#99FF99; margin:0; padding:0">　</td>
              					<td>Normal</td>
              					<td style="background:#FFFF00; margin:0; padding:0">　</td>
              					<td style="background:#FFFF99; margin:0; padding:0">　</td>
              					<td>Sensitive</td>
              					<td style="background:#FF9900; margin:0; padding:0">　</td>
              					<td>Bad</td>
              					<td style="background:#FF0000; margin:0; padding:0">　</td>
              					<td>Very Bad</td> 
              				</tr>
              			</table> 
            </div>
			<div id="d3View"> </div>		
		</div> 
		
	<!-- Public Data PopUp --> 
		<div id="setting" data-role="popup" data-dismissible="false" data-overlay-theme="a" style="width:300px">
		<a href="#" data-rel="back" data-role="button" data-them="a" data-icon="delete" data-iconpos="notext" class="ui-btn-right">Close</a>
		<h1 class="egov-align-center">세팅</h1>
		
		<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">
			<input type="checkbox" name="keyValue" id="chkkeyValue" class="custom" checked/>
			<label for="chkkeyValue">키값</label>
			<input type="checkbox" name="dateValue" id="chkdateValue" class="custom" checked/>
			<label for="chkdateValue">날짜</label>
			<input type="checkbox" name="timeValue" id="chktimeValue" class="custom" checked/>
			<label for="chktimeValue">시간</label>
		</fieldset> 
		
		<fieldset data-role="controlgroup" data-type="horizontal" class="egov-align-center">
			<input type="radio" name="visType" id="chart" class="custom"/>
			<label for="chart">Chart</label>
			<input type="radio" name="visType" id="map" class="custom" checked/>
			<label for="map">Map</label>
			<input type="radio" name="visType" id="chartAndMap" class="custom"/>
			<label for="chartAndMap">Chart & Map</label>
		</fieldset> 
		
		
		<!-- key values !!!!!!!!!!!!!!-->
		<input type="hidden" id="keyValue" data-key="keyValue" value="696e74727564657232303934">			
		<!-- 4a73434758696e7438316a5146744f - road , 696e74727564657232303934 - env -->
		
		<label for="dateValue">날짜 : (금일 날짜로 기본 세팅)</label>
		<input type="date" id="dateValue" data-key="dateValue"/>
	
		<label for="timeValue">시간 : (최신 데이터 시간)</label>
		<input type="time" id="timeValue" data-key="timeValue"> 
		
		
		<label for="envValue">환경정보:</label>
		<fieldset data-role="controlgroup" data-type="horizontal">
		<input type="radio" id="env-radio1" name="env" value="PM10" checked>
			<label for="env-radio1">PM10</label>
		<input type="radio" id="env-radio2" name="env" value="PM25">
			<label for="env-radio2">PM25</label>
		<input type="radio" id="env-radio3" name="env" value="SO2">
			<label for="env-radio3">SO2</label>
		</fieldset>
		<fieldset data-role="controlgroup" data-type="horizontal"> 
		<input type="radio" id="env-radio6" name="env" value="O3">
			<label for="env-radio6">O3</label>
		<input type="radio" id="env-radio4" name="env" value="NO2">
			<label for="env-radio4">NO2</label>
		<input type="radio" id="env-radio5" name="env" value="CO">
			<label for="env-radio5">CO</label>
		</fieldset>
		
		<a href="#" data-popup="setting" data-role="button" id="serviceName" data-key="serviceName" 
			data-name="SeoulpublicOpenData" onclick="hmw.publicOpenData(this)">세팅 완료/값 불러오기</a>
		</div> 
		
	<!-- Public Data PopUp --> 
		<div data-role="popup" id="select">
			<ul data-role="listview">
				<li><a href="#mapList">지도 목록</a></li>
				<li><a href="#layerList">공간정보데이터 목록</a></li>
				<li><a href="#opendataList">공공오픈데이터 목록</a></li>
			</ul>
		</div>	 
	<!-- List Panel -->
		<div data-role="panel" data-display="overlay" id="mapList" style="padding:0;">
				<ul data-role="listview">
					<li data-theme="g" data-icon="delete" style="height: 2.8em;">
					  <a href="#" data-rel="close" style="color:rgb(255, 255, 255);">Close menu</a>
					</li>
					<li data-theme="z" data-role="list-divider">배경 지도</li>
					<li><a href="#" data-id="osm"  onclick="hmw.baseMap('osm')">OpenStreetMap</a></li>
					<li><a href="#"  onclick="hmw.baseMap('vworld')">V-World</a></li>
					<li data-theme="z" data-role="list-divider">WMS</li> 
					<li><a href="#vworldList"  data-rel="popup" data-position-to="window" 
					     onclick="hmw.wmsMap.vworld('vworldList','E9CACC10-B443-30E1-9E2E-9E18F49049CA')" >V-World</a></li>
					<!-- <li><a href="#vworldList"  data-rel="popup" data-position-to="window" >V-World</a></li>-->
				</ul>
		</div>  
	<!-- List Panel -->
		<div data-role="panel" data-display="overlay" id="layerList" style="padding:0;">
				<ul data-role="listview">
					<li data-theme="g" data-icon="delete" style="height: 2.8em;"><a href="#" data-rel="close" style="color:rgb(255, 255, 255);">Close menu</a></li>
					<li data-theme="z" data-role="list-divider">기본 제공 데이터</li>
						<li><a href="#" data-id="Seoul_si" data-name="loadVector" onclick="hmw.geoServerProcess(this)">서울특별시</a></li>
						<li><a href="#" data-id="Incheon_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">인천광역시</a></li>
						<li><a href="#" data-id="Gyeonggi_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">경기도</a></li>
						<li><a href="#" data-id="Sejong_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">세종시</a></li>
						<li><a href="#" data-id="Chungcheongnam_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">충청남도</a></li>
						<li><a href="#" data-id="Jellanam_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">전라남도</a></li>
						<li><a href="#" data-id="Busan_dong" data-name="loadVector" onclick="hmw.geoServerProcess(this)">부산</a></li>
				<!--<li data-theme="z" data-role="list-divider">사용자 업로드 데이터</li>
				 	<li><a href="#" data-key="workspaceName" data-value="user" data-name="createWorkspace" onclick="hmw.geoServerProcess(this)">test</a></li> -->  
				</ul>
		</div>  
	<!-- List Panel -->
		<div data-role="panel" data-display="overlay" id="opendataList" style="padding:0;">
				<ul data-role="listview">
					<li data-theme="g" data-icon="delete" style="height: 2.8em;"><a href="#" data-rel="close" style="color:rgb(255, 255, 255);">Close menu</a></li>
					<li data-theme="z" data-role="list-divider">서울공공오픈데이터</li>
					<li><a href="#setting" data-rel="popup" data-position-to="window" data-value="TimeAverageAirQuality" onclick="beforeProcess.popupOpen(this)">실시간 서울 대기환경</a></li>
					<li><a href="#setting" data-rel="popup" data-position-to="window" data-value="RealtimeRoadsideStation"  onclick="beforeProcess.popupOpen(this)">도로변 측정소별 실시간 대기환경</a></li>
				</ul>
		</div>  
	
	<div data-role="header" class="ui-body-g center" data-position="inline" > 
 	 	<a href="#select" data-role="button" data-rel="popup"  data-transition="slidedown" data-theme="g">메뉴</a> 
		<h4>지도 시각화</h4>
		<!-- <a href="logout.do" data-role="button" data-theme="g" >로그아웃</a> -->
		<a href="#publicDataView" data-role="button" data-theme="g" >공공오픈데이터</a>
	</div>	
	
	<!-- Map Div -->
	<div data-role="content" style="padding:0; margin:0;">
		<div id="map" style="padding:0; margin:0;">
		</div>		
	</div>
	<div data-role="footer" class="ui-body-g center" data-position="inline"  data-tap-toggle="false" >
		<h4>.</h4>
	</div>
</div>
</body>
</html>
