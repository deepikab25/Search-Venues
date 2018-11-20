package com.foursquare.Model.Search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MetaDTO implements Serializable {
    @SerializedName("code")
    private String code;

    @SerializedName("requestId")
    private String requestId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
