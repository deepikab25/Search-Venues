package com.foursquare.Core;



public class Constants {
    public static double DEFAULT_LATTITUDE = 0.0f;
    public static double DEFAULT_LONGITUDE = 0.0f;

    public static final int ERROR_NO_RESPONSE_FROM_SERVER = 0;
    public static final int ERROR_INCORRECT_REQUEST_ID = 1;
    public static final int ERROR_NO_DATA_FROM_SERVER = 2;
    public static final int ERROR_NO_VENUES = 3;
    public static final int SUCCESS = 4;
    public static final int ERROR_FAILED_TO_GET_COORDINATES = 5;
    public static final int ERROR_GPS_IS_OFF = 6;
    public static final String keywordsearched ="keyword_searched";
    public static final String tableName ="history";
    public static final String selectQuery="select distinct(keyword_searched) from history";
    public static final String selectQuerySearch="select distinct(keyword_searched) from history where keyword_searched like '%";
    public static final String selectQueryKeywordSearch="select keyword_searched from history where keyword_searched=";
    public static final String responseCode="response_code";
    public static final String venues="venues";

}
