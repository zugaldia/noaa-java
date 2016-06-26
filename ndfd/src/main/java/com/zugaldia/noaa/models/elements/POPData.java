package com.zugaldia.noaa.models.elements;

import com.zugaldia.noaa.models.elements.BaseNameValue;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/25/16.
 */
@Root(name = "probability-of-precipitation", strict = false)
public class POPData extends BaseNameValue {

    public POPData() {
    }

}
