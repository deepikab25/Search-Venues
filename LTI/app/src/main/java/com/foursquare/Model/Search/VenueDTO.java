package com.foursquare.Model.Search;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VenueDTO implements Serializable {
    @SerializedName("name")
    private String name;

    private LocationDTO location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
