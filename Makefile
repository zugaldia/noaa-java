URL_UNSUMMARIZED=http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php
URL_SUMMARIZED=http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdBrowserClientByDay.php

# The beginning time for which you want NDFD data. If the string is empty,
# the beginnng time is assumed to be the earliest available time in the
# database. Time should be in UTC time.
DATE_BEGIN=

# The ending time for which you want NDFD data. If the string is empty, the
# ending time is assumed to be the last available time in the database. Time
# should be in UTC time.
DATE_END=

maven-deployment:
	cd lib; mvn clean deploy

maven-release:
	cd lib; mvn nexus-staging:release

maven-cancel:
	cd lib; mvn nexus-staging:drop

install-local:
	mvn install:install-file -DgroupId=com.github.zugaldia.noaa -DartifactId=ndfd -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=lib/target/ndfd-1.0-SNAPSHOT.jar

test-fixtures:
	#
	# Unsummarized
	#

	# Single Point Unsummarized Data
	curl --output lib/src/test/fixtures/response-unsummarized-01.xml \
		"$(URL_UNSUMMARIZED)?lat=38.99&lon=-77.01&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# Multiple Point Unsummarized Data
	curl --output lib/src/test/fixtures/response-unsummarized-02.xml \
		"$(URL_UNSUMMARIZED)?listLatLon=38.99,-77.02%2039.70,-104.80%2047.6,-122.30&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&Unit=e&maxt=maxt&mint=mint"

	# Unsummarized Data for a Subgrid
	curl --output lib/src/test/fixtures/response-unsummarized-03.xml \
		"$(URL_UNSUMMARIZED)?lat1=35.00&lon1=-82.00&lat2=35.5&lon2=-81.50&resolutionSub=20.0&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# A List of NDFD Points for a Subgrid
	curl --output lib/src/test/fixtures/response-unsummarized-04.xml \
		"$(URL_UNSUMMARIZED)?listLat1=35.00&listLon1=-82.00&listLat2=35.5&listLon2=-81.50&resolutionList=20.0"

	# Unsummarized Data for a Line
	curl --output lib/src/test/fixtures/response-unsummarized-05.xml \
		"$(URL_UNSUMMARIZED)?endPoint1Lat=38.99&endPoint1Lon=-77.01&endPoint2Lat=39.99&endPoint2Lon=-78.01&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# A List of NDFD Points for a Line
	curl --output lib/src/test/fixtures/response-unsummarized-06.xml \
		"$(URL_UNSUMMARIZED)?listEndPoint1Lat=38.99&listEndPoint1Lon=-77.01&listEndPoint2Lat=39.99&listEndPoint2Lon=-78.01"

	# Unsummarized Data for One or More Zipcodes
	curl --output lib/src/test/fixtures/response-unsummarized-07.xml \
		"$(URL_UNSUMMARIZED)?zipCodeList=20910+25414&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# A List of NDFD Points for a Zipcode
	curl --output lib/src/test/fixtures/response-unsummarized-08.xml \
		"$(URL_UNSUMMARIZED)?listZipCodeList=20910+25414"

	# Unsummarized Data for a List of Cities
	curl --output lib/src/test/fixtures/response-unsummarized-09.xml \
		"$(URL_UNSUMMARIZED)?citiesLevel=12&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# A List of NDFD Points for a List of Cities
	curl --output lib/src/test/fixtures/response-unsummarized-10.xml \
		"$(URL_UNSUMMARIZED)?listCitiesLevel=12"

	# Unsummarized Data for a Subgrid Defined by a Center Point
	curl --output lib/src/test/fixtures/response-unsummarized-11.xml \
		"$(URL_UNSUMMARIZED)?centerPointLat=38.0&centerPointLon=-97.4&distanceLat=50.0&distanceLon=50.0&resolutionSquare=20.0&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint"

	# A List of NDFD Points for a Subgrid Defined by a Center Point
	curl --output lib/src/test/fixtures/response-unsummarized-12.xml \
		"$(URL_UNSUMMARIZED)?listCenterPointLat=38.0&listCenterPointLon=-77.00&listDistanceLat=30.0&listDistanceLon=30.0&listResolutionSquare=20.0"

	# A List of NDFD Points for the Corners of an NDFD Grid
	curl --output lib/src/test/fixtures/response-unsummarized-13.xml \
		"$(URL_UNSUMMARIZED)?sector=conus"

	# Unsummarized Data for a Single Time Encoded in dwGML
	curl --output lib/src/test/fixtures/response-unsummarized-14.xml \
		"$(URL_UNSUMMARIZED)?whichClient=GmlLatLonList&gmlListLatLon=38.99,-77.02%2039.70,-104.80%2047.6,-122.30&featureType=Forecast_Gml2Point&&begin=$(DATE_BEGIN)&end=$(DATE_END)&compType=Between&propertyName=maxt,wx"

	# Unsummarized Data for a Period of Time Encoded in dwGML
	curl --output lib/src/test/fixtures/response-unsummarized-15.xml \
		"$(URL_UNSUMMARIZED)?gmlListLatLon=38.99,-77.02+39.70,-104.80+47.6,-122.30&featureType=Forecast_Gml2Point&begin=$(DATE_BEGIN)&end=$(DATE_END)&compType=Between&propertyName=maxt,wx"


	#
	# Summarized
	#

	# Single Point Summarized Data
	curl --output lib/src/test/fixtures/response-summarized-01.xml \
		"$(URL_SUMMARIZED)?lat=38.99&lon=-77.01&format=24+hourly&numDays=7"

	# Multiple Point Summarized Data
	curl --output lib/src/test/fixtures/response-summarized-02.xml \
		"$(URL_SUMMARIZED)?listLatLon=38.99,-77.02+39.70,-104.80+47.6,-122.30&format=24+hourly&numDays=7"

	# Summarized Data for a Subgrid
	curl --output lib/src/test/fixtures/response-summarized-03.xml \
		"$(URL_SUMMARIZED)?lat1=35.00&lon1=-82.00&lat2=35.5&lon2=-81.50&resolutionSub=20.0&format=24+hourly&numDays=7"

	# Summarized Data for a Line
	curl --output lib/src/test/fixtures/response-summarized-04.xml \
		"$(URL_SUMMARIZED)?endPoint1Lat=38.99&endPoint1Lon=-77.01&endPoint2Lat=39.99&endPoint2Lon=-78.01&format=24+hourly&numDays=7"

	# Summarized Data for One or More Zipcodes
	curl --output lib/src/test/fixtures/response-summarized-05.xml \
		"$(URL_SUMMARIZED)?zipCodeList=20910+25414&format=24+hourly&numDays=7"

	# Summarized Data for a List of Cities
	curl --output lib/src/test/fixtures/response-summarized-06.xml \
		"$(URL_SUMMARIZED)?citiesLevel=12&format=24+hourly&numDays=7"

	# Summarized Data for a Subgrid Defined by a Center Point
	curl --output lib/src/test/fixtures/response-summarized-07.xml \
		"$(URL_SUMMARIZED)?centerPointLat=38.0&centerPointLon=-97.4&distanceLat=50.0&distanceLon=50.0&resolutionSquare=20.0&format=24+hourly&numDays=7"

	#
	# Other
	#

	# 10 common indicators + icons
	curl --output lib/src/test/fixtures/response-unsummarized-16.xml \
		"$(URL_UNSUMMARIZED)?lat=38.90962&lon=-77.04341&product=time-series&begin=$(DATE_BEGIN)&end=$(DATE_END)&maxt=maxt&mint=mint&temp=temp&appt=appt&pop12=pop12&snow=snow&rh=rh&wx=wx&wwa=wwa&sky=sky&icons=icons"
