var openGDSMGeoserver = {};

openGDSMGeoserver.wfs = function(olmap,url,workspace,layername,color,width,epsg){
					epsg = (typeof(espg) !== 'undefined') ? epsg : "EPSG:900913";
	
					vectorSource = new ol.source.ServerVector({
				format: new ol.format.GeoJSON(),
				loader: function(extent, resolution, projection){
					var urls = url+'geoserver/wfs?service=WFS&' +
					'version=1.1.0&request=GetFeature' +  
					'&typeNames='+workspace+':'+layername+ 
					'&outputFormat=text/javascript&format_options=callback:loadFeatures' +
					'&srsname='+epsg+'&bbox=' + extent.join(',') + ','+epsg; 
					$.ajax({ 
						url : urls,
						dataType: 'jsonp' 
					});  
				},
				strategy: ol.loadingstrategy.createTile(new ol.tilegrid.XYZ({
					maxZoom: 19
				})),
				projection: epsg
			}); 
			loadFeatures = function(response){   
				vectorSource.addFeatures(vectorSource.readFeatures(response));  
			};   
			var styles = [
			      	    new ol.style.Style({
			      		  fill: new ol.style.Fill({
			      		    color: color,
			      		  }),
			      		  stroke: new ol.style.Stroke({
			      		    color: '#000000',
			      		    width: 1
			      		  }) 
			      		})]; 
	      	var vectorTemp = new ol.layer.Vector({
	         	title:layername,
	       	   	source: vectorSource,
	       	   	style: styles
	          });
	      	olmap.addLayer(vectorTemp); 
}; 