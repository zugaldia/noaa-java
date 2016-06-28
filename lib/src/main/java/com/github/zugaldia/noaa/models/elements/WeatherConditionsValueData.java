package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "value", strict = false)
public class WeatherConditionsValueData {

    @Attribute(name = "coverage")
    private String coverage;

    @Attribute(name = "intensity")
    private String intensity;

    @Attribute(name = "weather-type")
    private String weatherType;

    @Attribute(name = "qualifier")
    private String qualifier;

    @Attribute(name = "additive", required = false)
    private String additive;

    @Element(name = "visibility", required = false)
    private WeatherConditionsVisibilityData visibility;

    public WeatherConditionsValueData() {
    }

    public String getCoverage() {
        return coverage;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getAdditive() {
        return additive;
    }

    public WeatherConditionsVisibilityData getVisibility() {
        return visibility;
    }

}
