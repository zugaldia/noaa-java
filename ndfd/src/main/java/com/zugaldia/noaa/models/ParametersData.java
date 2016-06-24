package com.zugaldia.noaa.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class ParametersData {

    @ElementListUnion({
            @ElementList(entry = "temperature", type = TemperatureData.class, inline = true)
    })
    private List<Object> list;

    public ParametersData() {
    }

    public List<Object> getList() {
        return list;
    }
}
