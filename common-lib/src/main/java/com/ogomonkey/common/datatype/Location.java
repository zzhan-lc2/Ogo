package com.ogomonkey.common.datatype;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private Double latDD;
    private Double lonDD;
    private Double elev; // in meter

    public Location(double latDD, double lonDD) {
        this.latDD = latDD;
        this.lonDD = lonDD;
    }
}
