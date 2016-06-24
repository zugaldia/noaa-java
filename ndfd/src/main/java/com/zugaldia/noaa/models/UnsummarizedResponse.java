package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/24/16.
 */
@Root(name = "dwml", strict = false)
public class UnsummarizedResponse {

    @Element(name = "head")
    private ResponseHead head;

    @Element(name = "data")
    private ResponseData data;

    public UnsummarizedResponse() {
    }

    public ResponseHead getHead() {
        return head;
    }

    public ResponseData getData() {
        return data;
    }
}
