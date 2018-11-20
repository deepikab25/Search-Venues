package com.foursquare.Model.Search;

import java.io.Serializable;
import java.util.List;


public class ResponseDTO implements Serializable {
    private List<VenueDTO> venues;

    public List<VenueDTO> getVenues() {
        return venues;
    }


}
