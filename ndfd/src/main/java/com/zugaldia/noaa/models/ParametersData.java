package com.zugaldia.noaa.models;

import com.zugaldia.noaa.models.elements.*;
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
            @ElementList(entry = "temperature", type = TemperatureData.class, inline = true),
            @ElementList(entry = "probability-of-precipitation", type = POPData.class, inline = true),
            @ElementList(entry = "humidity", type = HumidityData.class, inline = true),
            @ElementList(entry = "cloud-amount", type = CloudAmountData.class, inline = true),
            @ElementList(entry = "precipitation", type = PrecipitationData.class, inline = true)
    })
    private List<Object> list;

    public ParametersData() {
    }

    public List<Object> getList() {
        return list;
    }

    public Object getElement(String element) {
        for (Object o: list) {
            if (element.equals(ElementModel.MAXT)
                    && o.getClass().equals(TemperatureData.class)
                    && ((TemperatureData) o).getElementType().equals("maximum")) {
                return o;
            } else if (element.equals(ElementModel.MINT)
                    && o.getClass().equals(TemperatureData.class)
                    && ((TemperatureData) o).getElementType().equals("minimum")) {
                return o;
            } else if (element.equals(ElementModel.TEMP)
                    && o.getClass().equals(TemperatureData.class)
                    && ((TemperatureData) o).getElementType().equals("hourly")) {
                return o;
            } else if (element.equals(ElementModel.APPT)
                    && o.getClass().equals(TemperatureData.class)
                    && ((TemperatureData) o).getElementType().equals("apparent")) {
                return o;
            } else if (element.equals(ElementModel.POP12)
                    && o.getClass().equals(POPData.class)) {
                return o;
            } else if (element.equals(ElementModel.RH)
                    && o.getClass().equals(HumidityData.class)) {
                return o;
            } else if (element.equals(ElementModel.SKY)
                    && o.getClass().equals(CloudAmountData.class)) {
                return o;
            } else if (element.equals(ElementModel.SNOW)
                    && o.getClass().equals(PrecipitationData.class)
                    && ((PrecipitationData) o).getElementType().equals("snow")) {
                return o;
            }
        }

        return null;
    }
}
