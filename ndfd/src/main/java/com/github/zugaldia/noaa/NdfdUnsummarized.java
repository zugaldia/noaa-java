package com.github.zugaldia.noaa;

import com.github.zugaldia.noaa.models.ElementModel;
import com.github.zugaldia.noaa.models.UnsummarizedResponse;
import com.github.zugaldia.noaa.transformers.CustomDateTransformer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by antonio on 6/24/16.
 */
public class NdfdUnsummarized {

    private String baseUrl = Constants.BASE_URL;
    private boolean enableDebug = false;

    private Builder builder;
    private Call<UnsummarizedResponse> call;
    private UnsummarizedService service;

    private NdfdUnsummarized(Builder builder) {
        this.builder = builder;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isEnableDebug() {
        return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    private UnsummarizedService getService() {
        if (service != null) return service;

        Retrofit retrofit = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(getBaseUrl())
                .addConverterFactory(SimpleXmlConverterFactory.create(getSerializer()))
                .build();

        service = retrofit.create(UnsummarizedService.class);
        return service;
    }

    private Serializer getSerializer() {
        // Custom date transformer
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_RESPONSE);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        RegistryMatcher registry = new RegistryMatcher();
        registry.bind(Date.class, new CustomDateTransformer(simpleDateFormat));
        return new Persister(registry);
    }

    private OkHttpClient getClient() {
        if (isEnableDebug()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            return httpClient.build();
        } else {
            return new OkHttpClient();
        }
    }

    public Call<UnsummarizedResponse> getCall() {
        if (call != null) return call;

        call = getService().getCall(
                builder.getLat(), builder.getLon(), builder.getProduct(),
                builder.getUtcBegin(), builder.getUtcEnd(), builder.getUnit(),
                builder.isElementRequested(ElementModel.MAXT),
                builder.isElementRequested(ElementModel.MINT),
                builder.isElementRequested(ElementModel.TEMP),
                builder.isElementRequested(ElementModel.DEW),
                builder.isElementRequested(ElementModel.POP12),
                builder.isElementRequested(ElementModel.QPF),
                builder.isElementRequested(ElementModel.SKY),
                builder.isElementRequested(ElementModel.SNOW),
                builder.isElementRequested(ElementModel.WSPD),
                builder.isElementRequested(ElementModel.WDIR),
                builder.isElementRequested(ElementModel.WX),
                builder.isElementRequested(ElementModel.WAVEH),
                builder.isElementRequested(ElementModel.ICONS),
                builder.isElementRequested(ElementModel.RH),
                builder.isElementRequested(ElementModel.APPT),
                builder.isElementRequested(ElementModel.INCW34),
                builder.isElementRequested(ElementModel.INCW50),
                builder.isElementRequested(ElementModel.INCW64),
                builder.isElementRequested(ElementModel.CUMW34),
                builder.isElementRequested(ElementModel.CUMW50),
                builder.isElementRequested(ElementModel.CUMW64),
                builder.isElementRequested(ElementModel.CRITFIREO),
                builder.isElementRequested(ElementModel.DRYFIREO),
                builder.isElementRequested(ElementModel.CONHAZO),
                builder.isElementRequested(ElementModel.PTORNADO),
                builder.isElementRequested(ElementModel.PHAIL),
                builder.isElementRequested(ElementModel.PTSTMWINDS),
                builder.isElementRequested(ElementModel.PXTORNADO),
                builder.isElementRequested(ElementModel.PXHAIL),
                builder.isElementRequested(ElementModel.PXTSTMWINDS),
                builder.isElementRequested(ElementModel.PTOTSVRTSTM),
                builder.isElementRequested(ElementModel.PXTOTSVRTSTM),
                builder.isElementRequested(ElementModel.TMPABV14D),
                builder.isElementRequested(ElementModel.TMPBLW14D),
                builder.isElementRequested(ElementModel.TMPABV30D),
                builder.isElementRequested(ElementModel.TMPBLW30D),
                builder.isElementRequested(ElementModel.TMPABV90D),
                builder.isElementRequested(ElementModel.TMPBLW90D),
                builder.isElementRequested(ElementModel.PRCPABV14D),
                builder.isElementRequested(ElementModel.PRCPBLW14D),
                builder.isElementRequested(ElementModel.PRCPABV30D),
                builder.isElementRequested(ElementModel.PRCPBLW30D),
                builder.isElementRequested(ElementModel.PRCPABV90D),
                builder.isElementRequested(ElementModel.PRCPBLW90D),
                builder.isElementRequested(ElementModel.PRECIPA_R),
                builder.isElementRequested(ElementModel.SKY_R),
                builder.isElementRequested(ElementModel.TD_R),
                builder.isElementRequested(ElementModel.TEMP_R),
                builder.isElementRequested(ElementModel.WDIR_R),
                builder.isElementRequested(ElementModel.WSPD_R),
                builder.isElementRequested(ElementModel.WWA),
                builder.isElementRequested(ElementModel.WGUST),
                builder.isElementRequested(ElementModel.ICEACCUM),
                builder.isElementRequested(ElementModel.MAXRH),
                builder.isElementRequested(ElementModel.MINRH));
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

        // Date formatter
        private SimpleDateFormat simpleDateFormat;

        public Builder() {
            elements = new ArrayList<String>();
            simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_REQUEST);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        /*
         * Location
         */

        public Builder setLocation(double latitude, double longitude) {
            lat = latitude;
            lon = longitude;
            return this;
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

        public Builder setProduct(String product) {
            this.product = product;
            return this;
        }

        public String getUtcBegin() {
            return begin == null ? null : simpleDateFormat.format(begin);
        }

        public Builder setUtcBegin(Date begin) {
            this.begin = begin;
            return this;
        }

        public String getUtcEnd() {
            return begin == null ? null : simpleDateFormat.format(end);
        }

        public Builder setUtcEnd(Date end) {
            this.end = end;
            return this;
        }

        public String getUnit() {
            return unit;
        }

        public Builder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        /*
         * Elements
         */

        public Builder requestElement(String element) {
            if (!elements.contains(element)) {
                elements.add(element);
            }

            return this;
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
