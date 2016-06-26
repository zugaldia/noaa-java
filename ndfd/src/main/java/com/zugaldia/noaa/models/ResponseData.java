package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class ResponseData {

    @ElementList(entry = "time-layout", inline = true)
    private List<TimeLayoutData> layouts;

    @ElementList(entry = "parameters", inline = true)
    private List<ParametersData> parameters;

    public List<TimeLayoutData> getTimeLayouts() {
        return layouts;
    }

    public TimeLayoutData getTimeLayout(String key) {
        for (TimeLayoutData layout: layouts) {
            if (layout.getLayoutKey().equals(key)) {
                return layout;
            }
        }

        return null;
    }

    public List<ParametersData> getParameters() {
        return parameters;
    }

    public ParametersData getParameter(String location) {
        for (ParametersData parameter: parameters) {
            if (parameter.getApplicableLocation().equals(location)) {
                return parameter;
            }
        }

        return null;
    }

    public ParametersData getDefault() {
        return getParameter("point1");
    }
}
