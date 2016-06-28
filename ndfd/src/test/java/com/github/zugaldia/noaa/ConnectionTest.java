package com.github.zugaldia.noaa;

import com.github.zugaldia.noaa.models.UnsummarizedResponse;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 6/24/16.
 */
public class ConnectionTest {

    @Test
    public void testSanity() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder()
                .setLocation(38.90962, -77.04341)
                .build();

        client.setEnableDebug(true);

        Call<UnsummarizedResponse> call = client.getCall();
        assertEquals(call.request().url().toString(),
                "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?lat=38.90962&lon=-77.04341");

        Response<UnsummarizedResponse> response = call.execute();
        assertEquals(response.code(), 200);
    }

}
