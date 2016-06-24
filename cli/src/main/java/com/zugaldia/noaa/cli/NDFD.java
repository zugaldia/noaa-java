package com.zugaldia.noaa.cli;

import com.zugaldia.noaa.NdfdUnsummarized;
import com.zugaldia.noaa.models.UnsummarizedResponse;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by antonio on 6/24/16.
 */
public class NDFD {

    public static void main(String[] args) throws IOException {
        System.out.println("Launching NOAA's National Digital Forecast Database (NDFD)...");

        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        Response<UnsummarizedResponse> response = client.executeCall();
        System.out.println("Title: " + response.body().getHead().getProduct().getTitle());
    }

}
