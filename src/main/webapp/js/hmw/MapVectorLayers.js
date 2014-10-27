//http://113.198.80.60:8080/geoserver/wfs?service=wfs&version=1.1.0&request=GetFeature&typeNames=opengds:Seoul_si&outputFormat=text/javascript
var Layer = {};
var	LayerSources =null;
	Layer.vectorSource = null;
	Layer.jsonfile = null;
//192.168.0.9
//113.198.80.60:8080
	/**
	 * <context-param>
     *	<param-name>ENABLE_JSONP</param-name>
     *	<param-value>true</param-value>
  	 *	</context-param>
	 */
Layer.createLayer = function(obj,color,width){
	obj  = $(obj).attr('data-id'); 
	extent = Map.map.getView().calculateExtent(Map.map.getSize());
	console.log(extent);
	parseResponse = function(features){
		console.log(features);
		var canvasFunction = function(extent,resolution,pixelRatio,size,projection){
			var canvasWidth = size[0];
		    var canvasHeight = size[1];
		    console.log(extent);
		    var canvas = d3.select(document.createElement('canvas'));
		    canvas.attr('width', canvasWidth).attr('height', canvasHeight);

		    var context = canvas.node().getContext('2d');

		    var d3Projection = d3.geo.mercator().scale(1).translate([0, 0]);
		    var d3Path = d3.geo.path().projection(d3Projection);

		    var pixelBounds = d3Path.bounds(features);
		    var pixelBoundsWidth = pixelBounds[1][0] - pixelBounds[0][0];
		    var pixelBoundsHeight = pixelBounds[1][1] - pixelBounds[0][1];

		    var geoBounds = d3.geo.bounds(features);
		    var geoBoundsLeftBottom = ol.proj.transform(
		        geoBounds[0], 'EPSG:4326', projection);
		    var geoBoundsRightTop = ol.proj.transform(
		        geoBounds[1], 'EPSG:4326', projection);
		    var geoBoundsWidth = geoBoundsRightTop[0] - geoBoundsLeftBottom[0];
		    if (geoBoundsWidth < 0) {
		      geoBoundsWidth += ol.extent.getWidth(projection.getExtent());
		    }
		    var geoBoundsHeight = geoBoundsRightTop[1] - geoBoundsLeftBottom[1];

		    var widthResolution = geoBoundsWidth / pixelBoundsWidth;
		    var heightResolution = geoBoundsHeight / pixelBoundsHeight;
		    var r = Math.max(widthResolution, heightResolution);
		    var scale = r / (resolution / pixelRatio);

		    var center = ol.proj.transform(ol.extent.getCenter(extent),
		        projection, 'EPSG:4326');
		    d3Projection.scale(scale).center(center)
		        .translate([canvasWidth / 2, canvasHeight / 2]);
		    d3Path = d3Path.projection(d3Projection).context(context);
		    //d3Path(features); 
		    $.each(features.features, function(index, value){ 
		    	
		    	console.log(value.properties.SIG_KOR_NM.toString());
		    	var test = new String('은평구');
		    	if(value.properties.SIG_KOR_NM.toString()=='강동구'){
		    		console.log("eee");
		    		d3Path(value); 
			        context.fillStyle = '#8ED6FF';
			        context.fill();
			    	context.strokeStyle = '#ff0000';
			    	context.stroke(); 
		    	}
		    	else{
		    		console.log("eee1");
		    		d3Path(value); 
		    		context.stroke();
		    	}
		    });
		    //d3Path(features.features[0]); 
		    return canvas[0][0];
		};
		var layer = new ol.layer.Image({
		    source: new ol.source.ImageCanvas({
		      canvasFunction: canvasFunction,
		      projection: 'EPSG:900913'
		    })
		  });
		Map.map.addLayer(layer);
	};
	var url = 'http://113.198.80.9/geoserver/wfs';
	//var url = 'http://113.198.80.9/geoserver/wfs?service=wfs&version=1.1.0&request=GetFeature&typeNames=opengds:seoul_si&outputFormat=text/javascript';
	$.ajax({
		type:'GET',
		url:url, 
		data : {
			service : 'wfs',
			version : '1.1.0',
			request : 'getFeature',
			typeNames : 'opengds:'+obj,
			outputFormat : 'text/javascript'
		},
		dataType : 'jsonp',
		jsonpCallback : 'parseResponse',
		success:function(msg){ 
			console.log("OK"); 
		},
		error:function(xhr, status , err){
			console.log(status +';'+err);
		}
	});   
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
