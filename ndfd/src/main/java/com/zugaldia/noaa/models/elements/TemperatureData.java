package com.zugaldia.noaa.models.elements;

import com.zugaldia.noaa.models.elements.BaseNameValue;
import org.simpleframework.xml.*;

/**
 * Created by antonio on 6/24/16.
 */
@Root(name = "temperature", strict = false)
public class TemperatureData extends BaseNameValue {

    public TemperatureData() {
    }

}
