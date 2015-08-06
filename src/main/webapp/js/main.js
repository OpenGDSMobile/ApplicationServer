var openGDSMObj;

//배경지도 라디오 버튼 사용자 인터페이스 생성 함수
function mapSelectUI(openGDSMObj) {
    'use strict';
    var ui = new OGDSM.eGovFrameUI();
    //ui.baseMapRadioBox(openGDSMObj, 'mapSelect', 'OSM VWorld VWorld_m VWorld_s VWorld_g'); //현재.... 데이터 체크...
    ui.baseMapSelect(openGDSMObj, 'mapSelect', 'OSM VWorld VWorld_m VWorld_s VWorld_g'); //현재.... 데이터 체크...
}
//데이터 라디오 버튼 사용자 인터페이스 생성 함수
function mapAttrUI() {
    'use strict';
    var ui = new OGDSM.eGovFrameUI();
    var radioObj = ui.autoRadioBox('dataViewCheckBox', 'dataViewSelect', ['공간정보', '속성정보'], ['map', 'attr'], {horizontal : true});
    radioObj.bind('change', function () {
        if ($(this).val() === 'attr') {
            $('#attributeTable').removeClass('OGDSPosTransTopDownHide');
            $('#attributeTable').addClass('OGDSPosTransTopDownShow');
        } else {
            $('#attributeTable').removeClass('OGDSPosTransTopDownShow');
            $('#attributeTable').addClass('OGDSPosTransTopDownHide');

        }
    });

}
//브이월드 WMS 데이터 선택 사용자 인터페이스 생성 / 시각화 함수
function vworldWMSUI() {
    'use strict';
    $('#vworldSelect').empty();
    var ui = new OGDSM.eGovFrameUI();
    var externalServer = new OGDSM.externalConnection('vworldWMS');
    var wmsListIds = ui.vworldWMSList('vworldSelect');
    var processBtn = ui.autoButton('vworldSelect', 'vworldProcess', '시각화', '#', {
        theme : 'a'
    });
    processBtn.click(function () {
        var i, data = [];
        for (i = 0; i < wmsListIds.length; i++) {
            var tmpData = $('#' + wmsListIds[i] + ' option:selected').val();
            if (tmpData !== '') {
                data.push(tmpData);
            }
        }
        var wmsData = externalServer.vworldWMSLoad("9E21E5EE-67D4-36B9-85BB-E153321EEE65", "http://localhost", data);
        openGDSMObj.addMap(wmsData);
        $('#vworldList').popup('close');
    });
}
//지오서버 WFS 데이터 시각화 함수
function wfsLoad(str, type) {
    'use strict';
    type = (typeof (type) !== 'undefined') ? type : 'polygon';
    var r = Math.floor(Math.random() * 256),
        g = Math.floor(Math.random() * 256),
        b = Math.floor(Math.random() * 256);
    var color = 'rgb(' + r + ',' + g + ',' + b + ')';
    var externalServer = new OGDSM.externalConnection();
    var ui = new OGDSM.eGovFrameUI();
    externalServer.geoServerWFSLoad(openGDSMObj, geoServerAddr, 'opengds', str, { type : type, color : color});

}
//서울 열린데이터 광장 데이터 선택 사용자 인터페이스 생성 / 시각화 함수
function createSeoulPublicAreaEnvUI() {
    'use strict';
    $('#setting').empty();
    var ui = new OGDSM.eGovFrameUI();
    var envIds = ui.seoulEnvironment('setting');
    var processBtn = ui.autoButton('setting', 'process', '시각화', '#', {
        theme : 'a'
    });
    var externalServer = new OGDSM.externalConnection();
    var colors = ['#0090ff', '#008080', '#4cff4c', '#99ff99', '#FFFF00', '#FFFF99', '#FF9900', '#FF0000'],
        ranges = [ [15, 30, 55, 80, 100, 120, 200],    //PM10, PM25
                  [1, 2, 5.5, 9, 10.5, 12, 15],        //CO
                  [0.015, 0.03, 0.05, 0.06, 0.1045, 0.15, 0.2],    //NO2
                  [0.01, 0.02, 0.035, 0.05, 0.075, 0.1, 0.15],     //SO2
                  [0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.3] ];     //O3
    processBtn.click(function () {
        $('#setting').popup("close");
        var apiKey = '6473565a72696e7438326262524174',
            addr = serverAddr + '/SeoulOpenData.do';
        var visualType = $('input[name=' + envIds[0].attr('name') + ']:checked').val(),
            environmentType = $('input[name=' + envIds[3].attr('name') + ']:checked').val(),
            date = envIds[1].val(),
            time = envIds[2].val();
        time = time.substr(0,3);
        time = time.concat('00');
        console.log(time);
        switch (environmentType) {
        case 'PM10':
            ranges = ranges[0];
            break;
        case 'PM25':
            ranges = ranges[0];
            break;
        case 'CO':
            ranges = ranges[1];
            break;
        case 'NO2':
            ranges = ranges[2];
            break;
        case 'SO2':
            ranges = ranges[3];
            break;
        case 'O3':
            ranges = ranges[4];
            break;
        }
        externalServer.seoulEnvironmentLoad(addr, apiKey, environmentType, date, time, function (resultData) {
            var xyData = OGDSM.jsonToArray(resultData, environmentType, 'MSRSTE_NM');
            if (visualType === 'map') {
                externalServer.geoServerWFSLoad(openGDSMObj, geoServerAddr, 'opengds', 'seoul_sig', {
                    callback : function (layer) {
                        openGDSMObj.changeWFSStyle('seoul_sig', colors, {
                            opt : 0.6,
                            attr : 'sig_kor_nm',
                            range : ranges,
                            xyData : xyData
                        });
                    }
                }); 
                $('#d3viewonMap').show();
            } else if (visualType === 'chart') {
                openGDSMObj.barChart("d3View", xyData, ranges, colors); 

                $("#d3View").css('overflow-y', 'scroll');
                $("#d3View").css('max-height', ($(window).height() - 200) + 'px');
                
                $('#dataSelect').popup('open');
            }
        });
    });
}
function createPublicPortalUI() {
    'use strict';
    $('#setting').empty();
    var ui = new OGDSM.eGovFrameUI();
    var envIds = ui.dataPortalEnvironment('setting');
    var processBtn = ui.autoButton('setting', 'process', '시각화', '#', {
        theme : 'a'
    });
    var externalServer = new OGDSM.externalConnection();
    var colors = ['#0090ff', '#008080', '#4cff4c', '#99ff99', '#FFFF00', '#FFFF99', '#FF9900', '#FF0000'],
        ranges = [ [15, 30, 55, 80, 100, 120, 200],    //PM10, PM25
                  [1, 2, 5.5, 9, 10.5, 12, 15],        //CO
                  [0.015, 0.03, 0.05, 0.06, 0.1045, 0.15, 0.2],    //NO2
                  [0.01, 0.02, 0.035, 0.05, 0.075, 0.1, 0.15],     //SO2
                  [0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.3] ];     //O3
    var layerName = '';
    processBtn.click(function () {
        $('#setting').popup("close");
        var apiKey = 'kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D',
            addr = serverAddr + '/PublicDataPortal.do';
        var visualType = $('input[name=' + envIds[0].attr('name') + ']:checked').val(),
            environmentType = $('input[name=' + envIds[2].attr('name') + ']:checked').val(),
            area = $('input[name=' + envIds[1].attr('name') + ']:checked').val();
        console.log(addr + ' ' + visualType + ' ' + environmentType + ' ' + area);
        switch (environmentType) {
        case 'pm10Value':
            ranges = ranges[0];
            break;
        case 'pm25Value':
            ranges = ranges[0];
            break;
        case 'coValue':
            ranges = ranges[1];
            break;
        case 'no2Value':
            ranges = ranges[2];
            break;
        case 'so2Value':
            ranges = ranges[3];
            break;
        case 'o3Value':
            ranges = ranges[4];
            break;
        }
        console.log(area);
        switch (area) {
        case '서울':	layerName = 'seoul_sig'; break;
        case '인천':	layerName ='incheon_emd'; break;
        case '경기':	layerName ='gyeonggi_emd';  break;
        case '강원':	layerName ='gangwon_emd';	break;
        case '충남':	layerName ='chungnam_emd';	break;
        case '세종':	layerName ='sejong_emd';	break;
        case '충북':	layerName ='chungbuk_emd';	break;
        case '대전':	layerName ='daejeon_emd';	break;
        case '경북':	layerName ='gyeongbuk_emd';	break;
        case '전북':	layerName ='jeonbuk_emd';	break;
        case '대구':	layerName ='daegu_emd';	break;
        case '울산':	layerName ='ulsan_emd';	break;
        case '전남':	layerName ='geonnam_emd';	break;
        case '광주':	layerName ='gangju_emd';	break;
        case '경남':	layerName ='gyeongnam_emd';	break;
        case '부산':	layerName ='busan_emd';	break;
        case '제주':	layerName ='jeju_sig';	break;
        }
        console.log(layerName);
        externalServer.dataPortalEnvironmentLoad(addr, apiKey, environmentType, area, function (resultData) {
            var xyData = OGDSM.jsonToArray(resultData, environmentType, 'stationName');
            if (visualType === 'map') {
                externalServer.geoServerWFSLoad(openGDSMObj, geoServerAddr, 'opengds', layerName, {
                    callback : function (layer) {
                    	if(layerName =='seoul_sig' || layerName=='jeju_sig'){
	                        openGDSMObj.changeWFSStyle(layerName, colors, {
	                            opt : 0.6,
	                            attr : 'sig_kor_nm',
	                            range : ranges,
	                            xyData : xyData
	                        });
                    	} else{
	                        openGDSMObj.changeWFSStyle(layerName, colors, {
	                            opt : 0.6,
	                            attr : 'emd_kor_nm',
	                            range : ranges,
	                            xyData : xyData
	                        });
                    	}
                    }
                });
                $('#d3viewonMap').show();
            } else if (visualType === 'chart') {
                openGDSMObj.barChart("d3View", xyData, ranges, colors);

                $("#d3View").css('overflow-y', 'scroll');
                $("#d3View").css('max-height', ($(window).height() - 200) + 'px');
                
                $('#dataSelect').popup('open');
            }
        });
    });
}
$(function () {
    'use strict';

    $('.ui-content').height($.mobile.getScreenHeight() + 'px');

    //$('.ui-content').height('100%');
    
    openGDSMObj = new OGDSM.visualization('map', {
        layerListDiv : 'layerList',
        attrTableDiv : 'attributeTable',
        attrAddr : serverAddr + '/attrTable.do'
    }); //map div, layerList switch
    openGDSMObj.olMapView([127.010031, 37.582200], 'OSM', 'EPSG:900913'); //VWorld
    openGDSMObj.trackingGeoLocation(true);
    mapSelectUI(openGDSMObj);
    mapAttrUI();
   // openGDSMObj.updateLayoutSetting();
    /***************************************************/
	$('#d3viewonMap').hide();
	$("#d3viewonMap").attr('width', $(window).width() - 50);
	$('#d3viewonMap').css('top', $(window).height() - 60);
	/*
    $('#dataSelect').attr('width', $(window).width() - 50);
    $('#dataSelect').attr('height', $(window).height() - 500);
    */
    /***************************************************/
});
