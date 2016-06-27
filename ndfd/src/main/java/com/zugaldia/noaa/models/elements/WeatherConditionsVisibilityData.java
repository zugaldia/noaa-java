package com.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "visibility", strict = false)
public class WeatherConditionsVisibilityData {

    @Attribute(name = "units", required = false)
    private String units;

    // Not a double, this can be something like "6+"
    @Text(required = false)
    private String value;

    public WeatherConditionsVisibilityData() {
    }

    public String getUnits() {
        return units;
    }

    public String getValue() {
        return value;
    }
}
