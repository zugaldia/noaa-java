package com.github.zugaldia.noaa.transformers;

import org.simpleframework.xml.transform.Transform;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by antonio on 6/27/16.
 */
public class CustomDateTransformer implements Transform<Date> {

    private DateFormat df;

    public CustomDateTransformer(DateFormat df) {
        this.df = df;
    }

    public Date read(String value) throws Exception {
        return df.parse(value);
    }

    public String write(Date date) throws Exception {
        return df.format(date);
    }
}
