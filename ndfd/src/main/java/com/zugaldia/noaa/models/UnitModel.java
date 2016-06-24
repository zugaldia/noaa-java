package com.zugaldia.noaa.models;

/**
 * Created by antonio on 6/24/16.
 */
public class UnitModel {

    /*
     * The unit data is to be retrieved in. The default value is U.S. Standard, or English units ("e"). A string
     * value of "m" will return data in Metric, or SI units (The International System of Units). If the string is
     * empty, data will be returned in U.S. Standard units, thus the input is only needed for metric conversion.
     */

    public final static String US = "e";
    public final static String METRIC = "m";

}
