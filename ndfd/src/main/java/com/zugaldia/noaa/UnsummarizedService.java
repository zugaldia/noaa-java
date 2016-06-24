package com.zugaldia.noaa;

import com.zugaldia.noaa.models.UnsummarizedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antonio on 6/24/16.
 */
public interface UnsummarizedService {

    @GET("xml/sample_products/browser_interface/ndfdXMLclient.php")
    Call<UnsummarizedResponse> getCall(
            // Single Point Unsummarized Data
            @Query("lat") Double lat,
            @Query("lon") Double lon,

            // Common query elements
            @Query("product") String product,
            @Query("begin") String begin,
            @Query("end") String end,
            @Query("Unit") String unit,

            // NDFD elements
            // http://graphical.weather.gov/xml/docs/elementInputNames.php
            @Query("maxt") String maxt,
            @Query("mint") String mint,
            @Query("temp") String temp,
            @Query("dew") String dew,
            @Query("pop12") String pop12,
            @Query("qpf") String qpf,
            @Query("sky") String sky,
            @Query("snow") String snow,
            @Query("wspd") String wspd,
            @Query("wdir") String wdir,
            @Query("wx") String wx,
            @Query("waveh") String waveh,
            @Query("icons") String icons,
            @Query("rh") String rh,
            @Query("appt") String appt,
            @Query("incw34") String incw34,
            @Query("incw50") String incw50,
            @Query("incw64") String incw64,
            @Query("cumw34") String cumw34,
            @Query("cumw50") String cumw50,
            @Query("cumw64") String cumw64,
            @Query("critfireo") String critfireo,
            @Query("dryfireo") String dryfireo,
            @Query("conhazo") String conhazo,
            @Query("ptornado") String ptornado,
            @Query("phail") String phail,
            @Query("ptstmwinds") String ptstmwinds,
            @Query("pxtornado") String pxtornado,
            @Query("pxhail") String pxhail,
            @Query("pxtstmwinds") String pxtstmwinds,
            @Query("ptotsvrtstm") String ptotsvrtstm,
            @Query("pxtotsvrtstm") String pxtotsvrtstm,
            @Query("tmpabv14d") String tmpabv14d,
            @Query("tmpblw14d") String tmpblw14d,
            @Query("tmpabv30d") String tmpabv30d,
            @Query("tmpblw30d") String tmpblw30d,
            @Query("tmpabv90d") String tmpabv90d,
            @Query("tmpblw90d") String tmpblw90d,
            @Query("prcpabv14d") String prcpabv14d,
            @Query("prcpblw14d") String prcpblw14d,
            @Query("prcpabv30d") String prcpabv30d,
            @Query("prcpblw30d") String prcpblw30d,
            @Query("prcpabv90d") String prcpabv90d,
            @Query("prcpblw90d") String prcpblw90d,
            @Query("precipa_r") String precipa_r,
            @Query("sky_r") String sky_r,
            @Query("td_r") String td_r,
            @Query("temp_r") String temp_r,
            @Query("wdir_r") String wdir_r,
            @Query("wspd_r") String wspd_r,
            @Query("wwa") String wwa,
            @Query("wgust") String wgust,
            @Query("iceaccum") String iceaccum,
            @Query("maxrh") String maxrh,
            @Query("minrh") String minrh
    );

}
