package com.zugaldia.noaa.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by antonio on 6/24/16.
 */
@Root(strict = false)
public class HeadProduct {

    @Element(name = "title")
    private String title;

    @Element(name = "field")
    private String field;

    @Element(name = "category")
    private String category;

    @Element(name = "creation-date")
    private String creationDate;

    public String getTitle() {
        return title;
    }

    public String getField() {
        return field;
    }

    public String getCategory() {
        return category;
    }

    public String getCreationDate() {
        return creationDate;
    }

}
