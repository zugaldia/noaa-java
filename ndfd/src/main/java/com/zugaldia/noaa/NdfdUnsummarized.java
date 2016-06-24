package com.zugaldia.noaa;

import com.zugaldia.noaa.models.UnsummarizedResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by antonio on 6/24/16.
 */
public class NdfdUnsummarized {

    private Builder builder;
    private Call<UnsummarizedResponse> call;
    private UnsummarizedService service;

    private String baseUrl = Constants.BASE_URL;

    private NdfdUnsummarized(Builder builder) {
        this.builder = builder;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private UnsummarizedService getService() {
        if (service != null) return service;

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        service = retrofit.create(UnsummarizedService.class);
        return service;
    }

    private Call<UnsummarizedResponse> getCall() {
        if (call != null) return call;

        call = getService().getCall(
                builder.getLat(), builder.getLon(),
                builder.getProduct(), builder.getBegin(), builder.getEnd(), builder.getUnit(),
                builder.isElementRequested("MAXT"),
                builder.isElementRequested("MINT"),
                builder.isElementRequested("TEMP"),
                builder.isElementRequested("DEW"),
                builder.isElementRequested("POP12"),
                builder.isElementRequested("QPF"),
                builder.isElementRequested("SKY"),
                builder.isElementRequested("SNOW"),
                builder.isElementRequested("WSPD"),
                builder.isElementRequested("WDIR"),
                builder.isElementRequested("WX"),
                builder.isElementRequested("WAVEH"),
                builder.isElementRequested("ICONS"),
                builder.isElementRequested("RH"),
                builder.isElementRequested("APPT"),
                builder.isElementRequested("INCW34"),
                builder.isElementRequested("INCW50"),
                builder.isElementRequested("INCW64"),
                builder.isElementRequested("CUMW34"),
                builder.isElementRequested("CUMW50"),
                builder.isElementRequested("CUMW64"),
                builder.isElementRequested("CRITFIREO"),
                builder.isElementRequested("DRYFIREO"),
                builder.isElementRequested("CONHAZO"),
                builder.isElementRequested("PTORNADO"),
                builder.isElementRequested("PHAIL"),
                builder.isElementRequested("PTSTMWINDS"),
                builder.isElementRequested("PXTORNADO"),
                builder.isElementRequested("PXHAIL"),
                builder.isElementRequested("PXTSTMWINDS"),
                builder.isElementRequested("PTOTSVRTSTM"),
                builder.isElementRequested("PXTOTSVRTSTM"),
                builder.isElementRequested("TMPABV14D"),
                builder.isElementRequested("TMPBLW14D"),
                builder.isElementRequested("TMPABV30D"),
                builder.isElementRequested("TMPBLW30D"),
                builder.isElementRequested("TMPABV90D"),
                builder.isElementRequested("TMPBLW90D"),
                builder.isElementRequested("PRCPABV14D"),
                builder.isElementRequested("PRCPBLW14D"),
                builder.isElementRequested("PRCPABV30D"),
                builder.isElementRequested("PRCPBLW30D"),
                builder.isElementRequested("PRCPABV90D"),
                builder.isElementRequested("PRCPBLW90D"),
                builder.isElementRequested("PRECIPA_R"),
                builder.isElementRequested("SKY_R"),
                builder.isElementRequested("TD_R"),
                builder.isElementRequested("TEMP_R"),
                builder.isElementRequested("WDIR_R"),
                builder.isElementRequested("WSPD_R"),
                builder.isElementRequested("WWA"),
                builder.isElementRequested("WGUST"),
                builder.isElementRequested("ICEACCUM"),
                builder.isElementRequested("MAXRH"),
                builder.isElementRequested("MINRH"));
        return call;
    }

    public Response<UnsummarizedResponse> executeCall() throws IOException {
        return getCall().execute();
    }

    public void enqueueCall(Callback<UnsummarizedResponse> callback) {
        getCall().enqueue(callback);
    }

    public void cancelCall() {
        getCall().cancel();
    }

    public Call<UnsummarizedResponse> cloneCall() {
        return getCall().clone();
    }

    public static class Builder {

        // Single Point Unsummarized Data
        private Double lat = null;
        private Double lon = null;

        // Common query elements
        private String product = null;
        private Date begin = null;
        private Date end = null;
        private String unit = null;

        // Elements
        private ArrayList<String> elements;

        public Builder() {
            elements = new ArrayList<String>();
        }

        /*
         * Location
         */

        public void setLocation(double latitude, double longitude) {
            lat = latitude;
            lon = longitude;
        }

        public Double getLat() {
            return lat;
        }

        public Double getLon() {
            return lon;
        }

        /*
         * Common query elements
         */

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getBegin() {
            return begin == null ? null : new SimpleDateFormat(Constants.DATE_FORMAT).format(begin);
        }

        public void setBegin(Date begin) {
            this.begin = begin;
        }

        public String getEnd() {
            return begin == null ? null : new SimpleDateFormat(Constants.DATE_FORMAT).format(end);
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        /*
         * Elements
         */

        public void requestElement(String element) {
            if (!elements.contains(element)) {
                elements.add(element);
            }
        }

        public String isElementRequested(String element) {
            return (elements.contains(element) ? element : null);
        }

        /*
         * Build
         */

        public NdfdUnsummarized build() {
            return new NdfdUnsummarized(this);
        }
    }

}
