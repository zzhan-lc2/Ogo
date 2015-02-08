package com.ogomonkey.common.datatype;

import lombok.Data;

@Data
public class Phone {
    private String phoneNumber;
    private String extension;
    private String areaCode;
    private String countryCode;
}
