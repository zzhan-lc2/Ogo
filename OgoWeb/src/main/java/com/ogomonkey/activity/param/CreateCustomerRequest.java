package com.ogomonkey.activity.param;

import lombok.Data;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.CommType;

@Data
public class CreateCustomerRequest {
    String userLogin;
    String password;
    CommType commType;
    String commNumber;
    Address address;
}
