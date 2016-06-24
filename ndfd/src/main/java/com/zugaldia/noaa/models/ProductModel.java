package com.zugaldia.noaa.models;

/**
 * Created by antonio on 6/24/16.
 */
public class ProductModel {

    /*
     * There are two products. The “time-series” product returns all data between the start and end times for
     * the selected weather parameters. The “glance” product returns all data between the start and end times
     * for the parameters maxt, mint, sky, wx, and icons
     */

    public final static String TIME_SERIES = "time-series";

    public final static String GLANCE = "glance";

}
