var Layer = {};
var	LayerSources =null;
	Layer.vectorSource = null;
	 
//192.168.0.9
//113.198.80.60:8080
Layer.createLayer = function(obj,color,width){
	Layer.vectorSource = new ol.source.ServerVector({
		format: new ol.format.GeoJSON(),
		loader: function(extent, resolution, projection){
			//console.log('Loading Data: '+data);
			var url = 'http://113.198.80.60:8080/geoserver/wfs?service=WFS&' +
			'version=1.1.0&request=GetFeature&' + 
			//'typeNames=opengds:'+data+
			'typeNames=opengds:Seoul_dong'+
			'&outputFormat=application/json' + 
			'&srsname=EPSG:3857&bbox=' + extent.join(',') + ',EPSG:3857';
			//console.log(url);  
			$.ajaxPrefilter('json', function(options, orig, jqXHR) { 
		        return 'jsonp';
		    }); 
			$.ajax({
			//	url : $(obj).attr('data-name')+'.do',
				crossDomain: true,
				url : url,
				dataType: 'json',
				//success:loadFeatures 
			});
			
		},
		strategy: ol.loadingstrategy.createTile(new ol.tilegrid.XYZ({
			maxZoom: 19
		})),
		projection: 'EPSG:3857'
	}); 
	loadFeatures = function(response){  
		Layer.vectorSource.addFeatures(Layer.vectorSource.readFeatures(response)); 
	};
	
	var vectorTemp = new ol.layer.Vector({
    	title:$(obj).attr('data-id'),
 	   	source: Layer.vectorSource,
 	   	style: new ol.style.Style({
 		   stroke: new ol.style.Stroke({
 			   color:color,
 			   width:width
 		   })
 	   })
    });
    Map.map.addLayer(vectorTemp);
};

Layer.removeLayer = function(data){
	Map.map.removeLayer(data);
};

Layer.displayFeatureInfo = function(pixel){
	var feature = Map.map.forEachFeatureAtPixel(pixel, function(feature, layer){
		return feature;
	});
	
	if(feature){
		console.log(feature.get('EMD_KOR_NM'));
	}		
	else{
		
	}	
}; 
