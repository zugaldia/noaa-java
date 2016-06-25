package com.zugaldia.noaa.cli;

import com.zugaldia.noaa.NdfdUnsummarized;
import com.zugaldia.noaa.models.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by antonio on 6/24/16.
 */
public class NDFD {

    public static void main(String[] args) throws IOException {
        System.out.println("Launching request...");

        // Build client
        NdfdUnsummarized client = new NdfdUnsummarized.Builder()
                .setLocation(38.90962, -77.04341) // Dupont Circle, Washington, DC
                .setProduct(ProductModel.GLANCE)
                .setUnit(UnitModel.METRIC)
                .build();

        // Enable debug
        client.setEnableDebug(true);

        Response<UnsummarizedResponse> response = client.executeCall();
        List<Object> parameters = response.body().getData().getParameters().getList();
        System.out.println(String.format("Number of params: %d", parameters.size()));

        TemperatureData temperature = (TemperatureData) parameters.get(0);
        Integer[] values = temperature.getValues();
        String layoutKey = temperature.getTimeLayout();
        TimeLayoutData layout = response.body().getData().getTimeLayout(layoutKey);
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format("Temperature: %d - from %s to %s.",
                    values[i],
                    layout.getStartList().get(i),
                    layout.getEndList().get(i)));
        }
    }

}
