package com.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "hazard", strict = false)
public class HazardConditionsData {

    @Attribute(name = "hazardCode")
    private String hazardCode;

    @Attribute(name = "phenomena")
    private String phenomena;

    @Attribute(name = "significance")
    private String significance;

    @Attribute(name = "hazardType")
    private String hazardType;

    @Element(name = "hazardTextURL")
    private String hazardTextURL;

    public HazardConditionsData() {
    }

    public String getHazardCode() {
        return hazardCode;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public String getSignificance() {
        return significance;
    }

    public String getHazardType() {
        return hazardType;
    }

    public String getHazardTextURL() {
        return hazardTextURL;
    }
}
