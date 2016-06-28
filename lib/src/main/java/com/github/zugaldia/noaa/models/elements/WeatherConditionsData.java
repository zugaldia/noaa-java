package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "weather-conditions", strict = false)
public class WeatherConditionsData {

    @ElementList(entry = "value", required = false, inline = true)
    private List<WeatherConditionsValueData> values;

    public WeatherConditionsData() {
    }

    public List<WeatherConditionsValueData> getValues() {
        return values;
    }

}
