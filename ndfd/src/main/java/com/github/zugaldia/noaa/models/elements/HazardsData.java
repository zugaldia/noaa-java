package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "hazards", strict = false)
public class HazardsData {

    @Element(name = "name")
    private String name;

    @Attribute(name = "time-layout")
    private String timeLayout;

    @ElementList(name = "hazard-conditions", required = false, inline = true)
    private List<HazardConditions> hazardConditions;

    public HazardsData() {
    }

    public String getName() {
        return name;
    }

    public String getTimeLayout() {
        return timeLayout;
    }

    public List<HazardConditions> getHazardConditions() {
        return hazardConditions;
    }

    public HazardConditions getDefault() {
        return hazardConditions == null ? null : hazardConditions.get(0);
    }
}
