package com.zugaldia.noaa;

/**
 * Created by antonio on 6/24/16.
 */
public class Constants {

    public final static String BASE_URL = "http://graphical.weather.gov";

    // E.g. 2004-04-27T12:00 (time should be in UTC time)
    public final static String DATE_FORMAT_REQUEST = "yyyy-MM-dd'T'HH:mm";

    // E.g. 2016-06-24T08:00:00-04:00 (time should be in UTC time)
    public final static String DATE_FORMAT_RESPONSE = "yyyy-MM-dd'T'HH:mm:ssXXX";

}
