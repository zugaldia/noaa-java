package com.zugaldia.noaa.models;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
@Root(name = "temperature", strict = false)
public class TemperatureData {

    @Element(name = "name")
    private String name;

    @ElementList(entry = "value", inline=true)
    private List<Integer> values;

    @Attribute(name = "type")
    private String temperatureType;

    @Attribute(name = "units")
    private String units;

    @Attribute(name = "time-layout")
    private String timeLayout;

    public TemperatureData() {
    }

    public String getName() {
        return name;
    }

    public Integer[] getValues() {
        return values.toArray(new Integer[values.size()]);
    }

    public String getTemperatureType() {
        return temperatureType;
    }

    public String getUnits() {
        return units;
    }

    public String getTimeLayout() {
        return timeLayout;
    }

}
