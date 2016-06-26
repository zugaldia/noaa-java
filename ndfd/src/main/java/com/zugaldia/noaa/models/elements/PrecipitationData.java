package com.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/25/16.
 */
@Root(name = "precipitation", strict = false)
public class PrecipitationData extends BaseNameValue {

    public PrecipitationData() {
    }

}
