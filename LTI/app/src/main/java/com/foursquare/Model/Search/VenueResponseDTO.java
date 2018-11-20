package com.foursquare.Model.Search;

import java.io.Serializable;


public class VenueResponseDTO implements Serializable {
    private MetaDTO meta;

    private ResponseDTO response;

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    public ResponseDTO getResponse() {
        return response;
    }

    public void setResponse(ResponseDTO response) {
        this.response = response;
    }
}

