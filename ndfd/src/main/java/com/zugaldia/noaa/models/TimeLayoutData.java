package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class TimeLayoutData {

    @Element(name = "layout-key")
    private String layoutKey;

    @ElementList(entry = "start-valid-time", type = String.class, inline=true)
    private List<String> startList;

    @ElementList(entry = "end-valid-time", type = String.class, inline=true)
    private List<String> endList;

    public TimeLayoutData() {
    }

    public String getLayoutKey() {
        return layoutKey;
    }

    public List<String> getStartList() {
        return startList;
    }

    public List<String> getEndList() {
        return endList;
    }

}
