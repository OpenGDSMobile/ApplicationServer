var Layer = {};
var	LayerSources =null;
	Layer.vectorSource = null;
	 
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
	Layer.vectorSource = new ol.source.ServerVector({
		format: new ol.format.GeoJSON(),
		loader: function(extent, resolution, projection){
			//console.log('Loading Data: '+data);
			var url = 'http://113.198.80.60:8080/geoserver/wfs?service=WFS&' +
			'version=1.1.0&request=GetFeature' +  
			'&typeNames=opengds:'+obj+
			//'&outputFormat=text/javascript' + 
			'&outputFormat=text/javascript&format_options=callback:loadFeatures' +
			'&srsname=EPSG:900913&bbox=' + extent.join(',') + ',EPSG:900913'; 
			$.ajax({ 
				url : url,
				dataType: 'jsonp' 
			}); 
		},
		strategy: ol.loadingstrategy.createTile(new ol.tilegrid.XYZ({
			maxZoom: 19
		})),
		projection: 'EPSG:900913'
	}); 
	loadFeatures = function(response){  
		Layer.vectorSource.addFeatures(Layer.vectorSource.readFeatures(response)); 
	};
	
	var vectorTemp = new ol.layer.Vector({
    	title:obj,
 	   	source: Layer.vectorSource,
 	   	style: new ol.style.Style({
 		   stroke: new ol.style.Stroke({
 			   color:color,
 			   width:width
 		   })
 	   })
    });
    Map.map.addLayer(vectorTemp);
	
	/*
	d3.json('1.json',function(error,us){
		//console.log(d3.geo.mercator());
		//var features = topojson.feature(us, us.objects.counties);
		var features = us;
		
		var canvasFunction = function(extent,resolution,pixelRatio,size,projection){
			var canvasWidth = size[0];
		    var canvasHeight = size[1];

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
		    d3Path(features);
		    context.stroke();

		    return canvas[0][0];
		};
		var layer = new ol.layer.Image({
		    source: new ol.source.ImageCanvas({
		      canvasFunction: canvasFunction,
		      projection: 'EPSG:900913'
		    })
		  });
		Map.map.addLayer(layer);
	});
	*/	
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
