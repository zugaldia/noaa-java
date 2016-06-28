package com.github.zugaldia.noaa.models.elements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/26/16.
 */
@Root(name = "conditions-icon", strict = false)
public class ConditionsIconData {

    @Element(name = "name")
    private String name;

    @ElementList(entry = "icon-link", inline=true)
    private List<String> iconLinks;

    @Attribute(name = "type")
    private String elementType;

    @Attribute(name = "time-layout")
    private String timeLayout;

    public ConditionsIconData() {
    }

    public String getName() {
        return name;
    }

    public List<String> getIconLinks() {
        return iconLinks;
    }

    public String getElementType() {
        return elementType;
    }

    public String getTimeLayout() {
        return timeLayout;
    }

}
