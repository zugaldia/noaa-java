package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class TimeLayoutData {

    @Element(name = "layout-key")
    private String layoutKey;

    @ElementList(entry = "start-valid-time", inline = true)
    private List<Date> startList;

    @ElementList(entry = "end-valid-time", inline = true, required = false)
    private List<Date> endList;

    public TimeLayoutData() {
    }

    public String getLayoutKey() {
        return layoutKey;
    }

    public List<Date> getUtcStartList() {
        return startList;
    }

    public List<Date> getUtcEndList() {
        return endList;
    }
}
