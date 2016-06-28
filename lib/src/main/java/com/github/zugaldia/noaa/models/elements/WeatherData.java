package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "weather", strict = false)
public class WeatherData {

    @Element(name = "name")
    private String name;

    @ElementList(entry = "weather-conditions", inline = true)
    private List<WeatherConditionsData> weatherConditions;

    @Attribute(name = "time-layout")
    private String timeLayout;

    public WeatherData() {
    }

    public String getName() {
        return name;
    }

    public List<WeatherConditionsData> getWeatherConditions() {
        return weatherConditions;
    }

    public String getTimeLayout() {
        return timeLayout;
    }

}
