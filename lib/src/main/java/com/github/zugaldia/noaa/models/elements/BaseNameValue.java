package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by antonio on 6/25/16.
 */
public class BaseNameValue {

    @Element(name = "name")
    private String name;

    @ElementList(entry = "value", inline=true)
    private List<Double> values;

    @Attribute(name = "type")
    private String elementType;

    @Attribute(name = "units")
    private String units;

    @Attribute(name = "time-layout")
    private String timeLayout;

    public String getName() {
        return name;
    }

    public Double[] getValues() {
        return values.toArray(new Double[values.size()]);
    }

    public String getElementType() {
        return elementType;
    }

    public String getUnits() {
        return units;
    }

    public String getTimeLayout() {
        return timeLayout;
    }
}
