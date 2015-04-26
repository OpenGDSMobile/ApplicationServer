# TODO: Add comment
# 
# Author: intruder
###############################################################################
library("maptools")
library("RCurl")
library("rjson")
library("MBA")
library("lattice")
library("fields")

mbaFunc <- function(){
	return(1)
	URL <- "http://openapi.seoul.go.kr:8088/696e74727564657232303934/json/TimeAverageAirQuality/1/25/201405051100"
	filename <- "seoul_env_position"
	mba.var <- "NO2"
	rowname <- "MSRSTE_NM"
	apitype <- "TimeAverageAirQuality"
	data.name <- "row"
	
	dir <- "./data/"
	resultdir <- 'result'
	
#	sink(paste(resultdir,'jpgList.xml',sep="/"))
#	cat('<?xml version="1.0" encoding="utf-8" ?>\n')
#	cat('<jpglist>\n')
#	for(i in 1:3){
#		cat(paste('\t<jpgfile path="../result/',i,'"/>\n',sep="")) 
#	}
#	cat('</jpglist>\n')
#	sink()
	
#	cat('</jpglist>\n')

}
testR <- function(){
	
	sink('jpgList.xml')
	cat('<?xml version="1.0" encoding="utf-8" ?>\n')
	#sink()
	a<-4
	return(a)
}
testR()