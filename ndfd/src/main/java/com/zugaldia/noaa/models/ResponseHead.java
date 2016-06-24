package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class ResponseHead {

    @Element(name = "product")
    HeadProduct product;

//    @Element(name = "source")
//    HeadSource source;

    public HeadProduct getProduct() {
        return product;
    }

}
