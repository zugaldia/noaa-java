package com.zugaldia.noaa.models.elements;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "hazard-conditions", strict = false)
public class HazardConditions {

    @ElementList(entry = "hazard", required = false, inline = true)
    private List<HazardConditionsData> hazardConditionsData;

    public HazardConditions() {
    }

    public List<HazardConditionsData> getHazardConditionsData() {
        return hazardConditionsData;
    }
}
