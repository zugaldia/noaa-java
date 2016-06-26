package com.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/25/16.
 */
@Root(name = "humidity", strict = false)
public class HumidityData extends BaseNameValue {

    public HumidityData() {
    }

}
